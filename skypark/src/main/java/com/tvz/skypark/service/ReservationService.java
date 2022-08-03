package com.tvz.skypark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tvz.skypark.dto.ReservationDetailsDto;
import com.tvz.skypark.model.Reservation;


@Service
public interface ReservationService {

	ReservationDetailsDto saveReservation(ReservationDetailsDto reservation);
	List<ReservationDetailsDto> findAllReservations();
	
	
	List<Reservation> findAllReservationsOfUser(Long userId);
	List<Reservation> getReservationsByUsername(String username);

//	List<ReservationItem> findAllReservationsOfUser(Long userId);

}
