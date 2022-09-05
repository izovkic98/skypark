package com.tvz.skypark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tvz.skypark.model.Discount;


@Service
public interface DiscountService {
	
	Discount findDiscountOfUser(Long userId);
	List <Discount> findAllDiscounts();
	Discount resetCode(Discount discount);
	Discount findById(Long discountId);

}
