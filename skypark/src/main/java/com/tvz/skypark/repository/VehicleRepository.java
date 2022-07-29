package com.tvz.skypark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvz.skypark.model.Vehicle;
import com.tvz.skypark.utils.ParkUtils.VehicleManufacturer;
import com.tvz.skypark.utils.ParkUtils.VehicleType;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	

}
