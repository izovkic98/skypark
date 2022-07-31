package com.tvz.skypark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.model.User;
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
	public ResponseEntity<?> signUp(@RequestBody User user){
		if(userService.findUserByUsername(user.getUsername()).isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
			
	}
	
	@PostMapping("sign-in")
	public ResponseEntity<?> signIn(@RequestBody User user){
		return new ResponseEntity<>(authenticationService.signInUserAndReturnJWT(user), HttpStatus.OK);
	}
	


}
