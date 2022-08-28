package com.tvz.skypark.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvz.skypark.model.Parking;
import com.tvz.skypark.repository.ParkingRepository;
import com.tvz.skypark.utils.ParkUtils.ParkingStatus;
import com.tvz.skypark.utils.ParkUtils.ParkingType;

@Service
public class ParkingServiceImpl implements ParkingService {
	
	@Autowired
	private final ParkingRepository parkingRepository;
	

	public ParkingServiceImpl(ParkingRepository parkingRepository) {
		this.parkingRepository = parkingRepository;
	}
	
	@Override
	public void saveParking(Parking parking) {
		parkingRepository.save(parking);	
	}
	
	@Override
	public void deleteParking(Long id) {
		parkingRepository.deleteById(id);
	}

	@Override
	public List<Parking> findAllFreeParkingsFirstZone() {
		return parkingRepository.findAll().stream()
										   .filter(parking -> parking.getParkingType().equals(ParkingType.I_ZONE))
										   .filter(parking -> parking.getParkingStatus().equals(ParkingStatus.Free))
										   .collect(Collectors.toList());
	}

	@Override
	public List<Parking> findAllFreeParkingsSecondZone() {
		return parkingRepository.findAll().stream()
										  .filter(parking -> parking.getParkingType().equals(ParkingType.II_ZONE))
										  .filter(parking -> parking.getParkingStatus().equals(ParkingStatus.Free))
										  .collect(Collectors.toList());
	}
	
	@Override
	public List<Parking> findAllOccupiedParkingsFirstZone() {
		return parkingRepository.findAll().stream()
										   .filter(parking -> parking.getParkingType().equals(ParkingType.I_ZONE))
										   .filter(parking -> parking.getParkingStatus().equals(ParkingStatus.Occupied))
										   .collect(Collectors.toList());
	}

	@Override
	public List<Parking> findAllOccupiedParkingsSecondZone() {
		return parkingRepository.findAll().stream()
										  .filter(parking -> parking.getParkingType().equals(ParkingType.II_ZONE))
										  .filter(parking -> parking.getParkingStatus().equals(ParkingStatus.Occupied))
										  .collect(Collectors.toList());
	}

	@Override
	public List<Parking> findAllParkingSpots() {
		return parkingRepository.findAll().stream()
				  						  .collect(Collectors.toList());
		
	}

	@Override
	public Parking updateParking(Parking updatedParking) {
		Parking parking = parkingRepository.findByIdLike(updatedParking.getId());
		parking.setParkingStatus(updatedParking.getParkingStatus());
		parking.setParkingType(updatedParking.getParkingType());
		
		return parkingRepository.save(parking);
	}

}
