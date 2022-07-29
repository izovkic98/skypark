package com.tvz.skypark.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tvz.skypark.model.User;
import com.tvz.skypark.utils.ParkUtils.Role;

@Service
public interface UserService {

	User saveUser(User user);

	Optional<User> findUserByUsername(String username);

	void changeRole(String username, Role role);

}
