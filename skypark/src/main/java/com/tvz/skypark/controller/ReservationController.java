package com.tvz.skypark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.dto.ReservationDetailsDto;
import com.tvz.skypark.security.UserPrinciple;
import com.tvz.skypark.service.ReservationService;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping
	public ResponseEntity<?> saveReservation(@RequestBody final ReservationDetailsDto reservation){
		return new ResponseEntity<>(reservationService.saveReservation(reservation), HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllReservationsOfUser(@AuthenticationPrincipal UserPrinciple userPrinciple){
		return ResponseEntity.ok(reservationService.findAllReservationsOfUser(userPrinciple.getId()));
	}
	
    @GetMapping("user/{username}")
    public List<ReservationDetailsDto> getReservationsByUsername(@PathVariable String username) {
        return reservationService.getReservationsByUsername(username);
    }
    
	@GetMapping("/all")
	public  List<ReservationDetailsDto> getAllReservations(){
		return reservationService.findAllReservations();
	}
	
}
