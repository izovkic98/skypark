package com.tvz.skypark.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tvz.skypark.utils.ParkUtils;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -8277720454969219271L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;

	@Column(name = "username", length = 50, nullable = false, unique = true)
	private String username;

	@Column(name = "password", length = 50, nullable = false)
	private String password;

	@Column(name = "address", length = 100, nullable = false)
	private String address;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "phone_number", length = 100, nullable = false)
	private String phoneNumber;

	@Column(name = "create_time", length = 100, nullable = false)
	private LocalDateTime createTime;

	@Column(name = "image_path", length = 500, nullable = false)
	private String imagePath;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Vehicle> vehicles;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Reservation> reservations;

	@Transient
	private String token;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private ParkUtils.Role role;
	


}
