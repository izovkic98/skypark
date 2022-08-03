package com.tvz.skypark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.dto.UserDetailsDto;
import com.tvz.skypark.security.UserPrinciple;
import com.tvz.skypark.service.UserService;
import com.tvz.skypark.utils.ParkUtils.Role;


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
	
    @GetMapping
    public List<UserDetailsDto> getAllUsers() {
        return userService.getAllUsers();
    }
	
	
}
