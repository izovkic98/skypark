package com.tvz.skypark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.service.ParkingService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/parking-management/") //pre-path
public class ParkingController {
	
	@Autowired
	private ParkingService userService;
	
//	@GetMapping("retreive/all")
//	public ResponseEntity<?> signUp(@RequestBody UserRegistrationDto user){
//		try {
//			return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
//		} catch ( UsernameOrEmailAreAlreadyTakenException|RequiredFieldIsEmptyException e) {
//			return new ResponseEntity<>(HttpStatus.CONFLICT);
//		}	
//	}
	
//	@GetMapping("retrieve/free")
//	public ResponseEntity<?> signIn(@RequestBody UserLoginDto user){
//		return new ResponseEntity<>(authenticationService.signInUserAndReturnJWT(user), HttpStatus.OK);
//	}
//	


}
