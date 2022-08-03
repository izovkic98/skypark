package com.tvz.skypark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tvz.skypark.dto.ReservationDetailsDto;


@Service
public interface ReservationService {

	ReservationDetailsDto saveReservation(ReservationDetailsDto reservation);
	List<ReservationDetailsDto> findAllReservations();
	
	
	List<ReservationDetailsDto> findAllReservationsOfUser(Long userId);
	List<ReservationDetailsDto> getReservationsByUsername(String username);

//	List<ReservationItem> findAllReservationsOfUser(Long userId);

}
