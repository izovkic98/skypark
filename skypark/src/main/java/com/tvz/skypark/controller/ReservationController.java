package com.tvz.skypark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.model.Reservation;
import com.tvz.skypark.security.UserPrinciple;
import com.tvz.skypark.service.ReservationService;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping
	public ResponseEntity<?> saveReservation(@RequestBody Reservation reservation){
		return new ResponseEntity<>(reservationService.saveReservation(reservation), HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllReservationsOfUser(@AuthenticationPrincipal UserPrinciple userPrinciple){
		return ResponseEntity.ok(reservationService.findAllReservationsOfUser(userPrinciple.getId()));
	}
	
}
