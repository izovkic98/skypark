package com.tvz.skypark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tvz.skypark.dto.UserDetailsDto;
import com.tvz.skypark.dto.UserRegistrationDto;
import com.tvz.skypark.exception.RequiredFieldIsEmptyException;
import com.tvz.skypark.exception.UsernameOrEmailAreAlreadyTakenException;
import com.tvz.skypark.utils.ParkUtils.Role;

@Service
public interface UserService {

	UserDetailsDto saveUser(UserRegistrationDto user) throws UsernameOrEmailAreAlreadyTakenException, RequiredFieldIsEmptyException;

	UserDetailsDto findUserByUsername(String username);
	
	List<UserDetailsDto> getAllUsers();

	void changeRole(String username, Role role);

}
