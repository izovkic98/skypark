package com.tvz.skypark.service;

import org.springframework.stereotype.Service;

import com.tvz.skypark.model.Discount;


@Service
public interface DiscountService {
	
	Discount findDiscountOfUser(Long userId);

}
