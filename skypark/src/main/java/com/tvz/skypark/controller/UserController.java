package com.tvz.skypark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.dto.UserDetailsDto;
import com.tvz.skypark.exception.InvalidConfirmedPasswordException;
import com.tvz.skypark.exception.InvalidOldPasswordException;
import com.tvz.skypark.exception.UserNotFoundException;
import com.tvz.skypark.security.UserPrinciple;
import com.tvz.skypark.service.UserService;
import com.tvz.skypark.utils.ParkUtils.Role;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/user")//pre-path
public class UserController {
	

	@Autowired
	private UserService userService;
	
	@PutMapping("change/{role}")
	private ResponseEntity<?> changeRoleEntity (@AuthenticationPrincipal UserPrinciple userPrinciple, @PathVariable Role role){
		userService.changeRole(userPrinciple.getUsername(), role);
		return ResponseEntity.ok(true);
	}
	
	
    @PutMapping("/change-password")
    public ResponseEntity<?> updatePassword(@Validated @RequestParam String newPassword, @Validated @RequestParam String oldPassword,
    		@Validated @RequestParam String confirmedNewPassword, @AuthenticationPrincipal UserPrinciple userPrinciple) {
    	
		UserDetailsDto user = userService.findUserByUsername(userPrinciple.getUsername());

		if (!userService.checkIfValidOldPassword(user, oldPassword)) {
			throw new InvalidOldPasswordException(
					"Password invalid for user with username:" + userPrinciple.getUsername());
		}
		
		if (!newPassword.contentEquals(confirmedNewPassword)) {
			throw new InvalidConfirmedPasswordException(
					"Confirmed password invalid for user with username:" + userPrinciple.getUsername());
		}
		userService.changeUserPassword(user, newPassword);

    	return new ResponseEntity<>( HttpStatus.OK);
    	
    }
    
	
    @GetMapping("/all")
    public List<UserDetailsDto> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/id/{id}")
    public UserDetailsDto getUserById(@PathVariable Long id) {
    		return userService.getUserById(id);

    }
    
    @PutMapping()
    public  ResponseEntity<?>  updateUser(@RequestBody UserDetailsDto updatedUser) {
    	return new ResponseEntity<>(userService.updateUser(updatedUser), HttpStatus.ACCEPTED);

    }
    
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long userId)
    {
    	try {
    		userService.deleteUser(userId);
    	} catch (UserNotFoundException e) {
    		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    	}
    	
        return new ResponseEntity<>( HttpStatus.OK);
    }
	
	
}
