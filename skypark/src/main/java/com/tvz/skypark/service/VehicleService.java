package com.tvz.skypark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tvz.skypark.model.Vehicle;

@Service
public interface VehicleService {

	Vehicle saveVehicle(Vehicle newVehicle);

	void deleteVehicle(Long vehicleId);

	List<Vehicle> findAllVehicles();

}
