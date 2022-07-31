package com.tvz.skypark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tvz.skypark.model.Reservation;


@Service
public interface ReservationService {

	Reservation saveReservation(Reservation reservation);

	List<Reservation> findAllReservations();

//	List<ReservationItem> findAllReservationsOfUser(Long userId);

}
