package com.tvz.skypark.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tvz.skypark.dto.UserDetailsDto;
import com.tvz.skypark.dto.UserRegistrationDto;
import com.tvz.skypark.exception.RequiredFieldIsEmptyException;
import com.tvz.skypark.exception.UserNotFoundException;
import com.tvz.skypark.exception.UsernameOrEmailAreAlreadyTakenException;
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
	public UserDetailsDto saveUser (UserRegistrationDto newUser) throws UsernameOrEmailAreAlreadyTakenException, RequiredFieldIsEmptyException  {

		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		boolean usernameInUse = userRepository.findByUsernameLike(newUser.getUsername())!=null;
		boolean emailInUse = userRepository.findByEmailLike(newUser.getEmail())!=null;
		if(newUser.getFirstName()==null || newUser.getLastName() == null || newUser.getEmail() == null || newUser.getUsername() == null || newUser.getPassword() == null)
			throw new RequiredFieldIsEmptyException("Neka polja su prazna");
		else if(usernameInUse && emailInUse)
			throw new UsernameOrEmailAreAlreadyTakenException("Korisničko ime i email se već koriste");
		else if(usernameInUse)
			throw new UsernameOrEmailAreAlreadyTakenException("Korisničko ime se već koristi");
		else if(emailInUse)
			throw new UsernameOrEmailAreAlreadyTakenException("Email se već koristi");
		
		return new UserDetailsDto(userRepository.save(new User(newUser)));
	
	}
	
	@Override
	public UserDetailsDto findUserByUsername(String username){
		
		User user = userRepository.findByUsernameLike(username);
		if(user!=null)
			return new UserDetailsDto(user);
		else
			return null;
	}
	
	@Override
	@Transactional
	public void changeRole( String username, Role newRole) {
		userRepository.updateUserRole(username, newRole);
	}

	@Override
	public List<UserDetailsDto> getAllUsers() {
		return userRepository.findAll().stream().map(UserDetailsDto::new).collect(Collectors.toList());
	}

	@Override
	public UserDetailsDto getUserById(Long userId){

		User user = userRepository.findById(userId).orElse(null);
		
		if (user != null) {
			return new UserDetailsDto(user);
		} else {
			return null;
		}
	}

	@Override
	public UserDetailsDto updateUser(UserDetailsDto updatedUser) {
		User user = userRepository.findByIdLike(updatedUser.getId());
		user.setFirstName(updatedUser.getFirstName());
		user.setLastName(updatedUser.getLastName());
		user.setUsername(updatedUser.getUsername());
		user.setEmail(updatedUser.getEmail());
		user.setPhoneNumber(updatedUser.getPhoneNumber());
		user.setRole(updatedUser.getRole());
		
		return new UserDetailsDto(userRepository.save(user));
	}

	@Override
	public void deleteUser(Long userId) throws UserNotFoundException {

			User user = userRepository.findById(userId).orElse(null);
			if (user != null) {
				userRepository.deleteById(userId);
			} else {
				throw new UserNotFoundException("User under id:" + userId + " is not found.");
			}
		
	}

	

}
