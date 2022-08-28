package com.tvz.skypark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tvz.skypark.model.Parking;


@Service
public interface ParkingService {
	void saveParking(Parking parking) ;
	void deleteParking(Long id) ;
	List<Parking> findAllFreeParkingsFirstZone();
	List<Parking> findAllFreeParkingsSecondZone();
	List<Parking> findAllOccupiedParkingsFirstZone();
	List<Parking> findAllOccupiedParkingsSecondZone();
	List<Parking> findAllParkingSpots();

}
