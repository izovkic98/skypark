package com.tvz.skypark.dto;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.tvz.skypark.model.User;
import com.tvz.skypark.utils.ParkUtils.Role;

public class UserDetailsDto implements Serializable{
	
	
    /**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -532488326957388540L;
	
	private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String address;
    private String email;
    private String phoneNumber;
    private LocalDateTime createTime;
    private Role role;

    public UserDetailsDto() {

    }

    public UserDetailsDto(User user) {
    	this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.createTime = user.getCreateTime();
        this.role = user.getRole();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	

}
