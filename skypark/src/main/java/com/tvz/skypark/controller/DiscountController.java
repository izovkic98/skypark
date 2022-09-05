package com.tvz.skypark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.model.Discount;
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
	
	@GetMapping("/id/{discountId}")
	public Discount getDiscountById(@PathVariable Long discountId) {
		return discountService.findById(discountId);
	}
	
	@GetMapping("/all")
	public List<Discount> getAllDiscounts() {
		return discountService.findAllDiscounts();
	}
	
	@PutMapping("/update")
	public Discount resetCode(@RequestBody Discount discount) {
		return discountService.resetCode(discount);
	}


}
