package com.tvz.skypark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvz.skypark.model.Discount;
import com.tvz.skypark.repository.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {
	
	@Autowired
	DiscountRepository discountRepository;

	
	@Override
	public Discount findDiscountOfUser(Long userId) {
		return discountRepository.findByUser_IdLike(userId);
	}
	


}
