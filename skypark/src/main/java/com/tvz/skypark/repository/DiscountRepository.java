package com.tvz.skypark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvz.skypark.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
