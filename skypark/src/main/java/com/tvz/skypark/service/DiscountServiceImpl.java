package com.tvz.skypark.service;

import java.util.List;

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


	@Override
	public List <Discount> findAllDiscounts() {
		return discountRepository.findAll();
	}


	@Override
	public Discount resetCode(Discount updatedDiscount) {
		Discount discount = discountRepository.findByIdLike(updatedDiscount.getId());
		discount.setCode(null);
		return discountRepository.save(discount);
	}


	@Override
	public Discount findById(Long discountId) {
		return discountRepository.findByIdLike(discountId);
	}
	


}
