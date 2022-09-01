package com.tvz.skypark.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
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
import com.tvz.skypark.dto.UserDetailsDto;
import com.tvz.skypark.dto.UserRegistrationDto;
import com.tvz.skypark.utils.ParkUtils.Role;
import com.tvz.skypark.utils.ParkUtils.Tier;

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

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	@Column(name = "address", length = 100, nullable = false)
	private String address;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;
	
	@Column(name = "loyalty_points", length = 100)
	private Integer loyaltyPoints;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tier", length = 100)
	private Tier tier;

	@Column(name = "phone_number", length = 100, nullable = false)
	private String phoneNumber;

	@Column(name = "create_time", length = 100, nullable = false)
	private LocalDate createTime;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,  mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Reservation> reservations;

	@Transient
	private String token;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;
	
	public User() {
		super();
	}

	public User(Long id, String firstName, String lastName, String username, String password, String address,
			String email, Integer loyaltyPoints, Tier tier, String phoneNumber, LocalDate createTime,
			List<Reservation> reservations, String token, Role role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.address = address;
		this.email = email;
		this.loyaltyPoints = loyaltyPoints;
		this.tier = tier;
		this.phoneNumber = phoneNumber;
		this.createTime = createTime;
		this.reservations = reservations;
		this.token = token;
		this.role = role;
	}

	
	public User(UserRegistrationDto userDto) {
		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
		this.username = userDto.getUsername();
		this.address = userDto.getAddress();
		this.phoneNumber = userDto.getPhoneNumber();
		this.email = userDto.getEmail();
		this.password = userDto.getPassword();
		this.createTime = LocalDate.now();
		this.role = Role.ADMIN;
	}
	
	public User(UserDetailsDto userDto) {
		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
		this.username = userDto.getUsername();
		this.address = userDto.getAddress();
		this.phoneNumber = userDto.getPhoneNumber();
		this.email = userDto.getEmail();
		this.password = userDto.getPassword();
		this.createTime = LocalDate.now();
		this.role = Role.USER;
	}


}
