package com.tvz.skypark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvz.skypark.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

	Discount findByIdLike(Long id);
	Discount findByUser_IdLike(Long userId);
	Discount findByCodeLike(String code);
	
 
}
