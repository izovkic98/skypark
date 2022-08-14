package com.tvz.skypark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tvz.skypark.dto.ReservationDetailsDto;
import com.tvz.skypark.exception.ReservationNotFoundException;


@Service
public interface ReservationService {

	ReservationDetailsDto saveReservation(ReservationDetailsDto reservation);
	List<ReservationDetailsDto> findAllReservations();
	ReservationDetailsDto updateReservation(ReservationDetailsDto updatedReservation);
	
	List<ReservationDetailsDto> findAllReservationsOfUser(Long userId);
	List<ReservationDetailsDto> getReservationsByUsername(String username);
	ReservationDetailsDto getReservationById(Long id);
	void deleteReservation(Long reservationId) throws ReservationNotFoundException;

//	List<ReservationItem> findAllReservationsOfUser(Long userId);

}
