package com.tvz.skypark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.security.UserPrinciple;
import com.tvz.skypark.service.DiscountService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/discounts") //pre-path
public class DiscountController {
	
	@Autowired
	private DiscountService discountService;
	
	@GetMapping("")
	public ResponseEntity<?> getUserDiscount(@AuthenticationPrincipal UserPrinciple userPrinciple) {
		return ResponseEntity.ok(discountService.findDiscountOfUser(userPrinciple.getId()));
	}


}
