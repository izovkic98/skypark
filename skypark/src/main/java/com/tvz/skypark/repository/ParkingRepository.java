package com.tvz.skypark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvz.skypark.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
	
 
}
