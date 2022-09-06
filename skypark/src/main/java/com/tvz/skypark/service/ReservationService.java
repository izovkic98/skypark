package com.tvz.skypark.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tvz.skypark.dto.ReservationDetailsDto;
import com.tvz.skypark.exception.FullParkingException;
import com.tvz.skypark.exception.ReservationDateIsIncorrectException;
import com.tvz.skypark.exception.ReservationNotFoundException;


@Service
public interface ReservationService {

	ReservationDetailsDto saveReservation(ReservationDetailsDto reservation) throws ReservationDateIsIncorrectException, IOException;
	List<ReservationDetailsDto> findAllReservations();
	ReservationDetailsDto updateReservation(ReservationDetailsDto updatedReservation) throws ReservationDateIsIncorrectException, FullParkingException;
	
	List<ReservationDetailsDto> findAllReservationsOfUser(Long userId);
	List<ReservationDetailsDto> getReservationsByUsername(String username);
	ReservationDetailsDto getReservationById(Long id);
	void deleteReservation(Long reservationId) throws ReservationNotFoundException;
	ReservationDetailsDto changeReservationStatus(ReservationDetailsDto updatedReservation) throws ReservationDateIsIncorrectException, FullParkingException;
	List<ReservationDetailsDto> findAllCurrentReservations(Long id);

}
