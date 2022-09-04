package com.tvz.skypark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.dto.UserLoginDto;
import com.tvz.skypark.dto.UserRegistrationDto;
import com.tvz.skypark.exception.RequiredFieldIsEmptyException;
import com.tvz.skypark.exception.UsernameOrEmailAreAlreadyTakenException;
import com.tvz.skypark.service.AuthenticationService;
import com.tvz.skypark.service.UserService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/authentication/") //pre-path
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("sign-up")
	public ResponseEntity<?> signUp(@RequestBody UserRegistrationDto user){
		try {
			return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
		} catch ( UsernameOrEmailAreAlreadyTakenException|RequiredFieldIsEmptyException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}	
	}
	
	@PostMapping("sign-in")
	public ResponseEntity<?> signIn(@RequestBody UserLoginDto user){
		return new ResponseEntity<>(authenticationService.signInUserAndReturnJWT(user), HttpStatus.OK);
	}
	


}
