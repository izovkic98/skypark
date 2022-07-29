package com.tvz.skypark.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvz.skypark.model.Vehicle;
import com.tvz.skypark.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private final VehicleRepository vehicleRepository;

	public VehicleServiceImpl(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	@Override
	public Vehicle saveVehicle (Vehicle newVehicle) {	
		newVehicle.setCreateTime(LocalDateTime.now());
		
		return vehicleRepository.save(newVehicle);
	}
	
	@Override
	public void deleteVehicle (Long vehicleId) {
		vehicleRepository.deleteById(vehicleId);
	}
	
	@Override
	public List<Vehicle> findAllVehicles(){
		return vehicleRepository.findAll();
	}

}
