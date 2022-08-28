package com.tvz.skypark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.model.Parking;
import com.tvz.skypark.service.ParkingService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/parking-spots") //pre-path
public class ParkingController {
	
	@Autowired
	private ParkingService parkingService;
	
	@GetMapping("/all")
	public List<Parking> getAllParkingSpots(){
		return parkingService.findAllParkingSpots();
	}
	
	@PostMapping()
	public ResponseEntity<?> createParking(@RequestBody Parking parking){
		parkingService.saveParking(parking);
		return new ResponseEntity<>( HttpStatus.CREATED); 
	}
	
	@DeleteMapping("{parkingId}")
	public ResponseEntity<?> deleteParking(@PathVariable Long parkingId){
		parkingService.deleteParking(parkingId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@PutMapping()
	public ResponseEntity<?> updateParking(@RequestBody Parking parking){
		parkingService.updateParking(parking);
		return new ResponseEntity<>(parkingService.updateParking(parking), HttpStatus.OK);
	}
	

}
