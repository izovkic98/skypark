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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tvz.skypark.dto.UserRegistrationDto;
import com.tvz.skypark.utils.ParkUtils.Role;

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

	private String address;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	private String phoneNumber;

	@Column(name = "create_time", length = 100, nullable = false)
	private LocalDateTime createTime;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Reservation> reservations;

	@Transient
	private String token;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;
	

	public User(UserRegistrationDto userDto) {
		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
		this.username = userDto.getUsername();
		this.email = userDto.getEmail();
		this.password = userDto.getPassword();
		this.createTime = LocalDateTime.now();
		this.role = Role.USER;
	}
	

}
