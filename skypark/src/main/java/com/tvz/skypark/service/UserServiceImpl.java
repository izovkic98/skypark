package com.tvz.skypark.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tvz.skypark.model.User;
import com.tvz.skypark.repository.UserRepository;
import com.tvz.skypark.utils.ParkUtils.Role;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public User saveUser (User newUser) {

		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		newUser.setRole(Role.USER);
		newUser.setCreateTime(LocalDateTime.now());
		
		return userRepository.save(newUser);
	
	}
	
	@Override
	public Optional<User> findUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	@Override
	@Transactional
	public void changeRole( String username, Role newRole) {
		userRepository.updateUserRole(username, newRole);
	}
	
	
	

}
