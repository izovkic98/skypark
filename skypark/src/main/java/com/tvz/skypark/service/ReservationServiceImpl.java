//package com.tvz.skypark.service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import org.springframework.stereotype.Service;
//import com.tvz.skypark.model.Reservation;
//import com.tvz.skypark.model.User;
//import com.tvz.skypark.repository.ReservationRepository;
//import com.tvz.skypark.repository.projection.ReservationItem;
//
//@Service
//public class ReservationServiceImpl implements ReservationService{
//	
//
//	private final ReservationRepository reservationRepository;
//
//	public ReservationServiceImpl(ReservationRepository reservationRepository) {
//		this.reservationRepository = reservationRepository;
//	}
//
//	@Override
//	public Reservation saveReservation(Reservation reservation) {
//
//		reservation.setReservationDate(LocalDateTime.now());
//
//		return reservationRepository.save(reservation);
//	}
//
//	@Override
//	public List <ReservationItem> findAllReservationsOfUser(Long userId) {
//		return reservationRepository.findAllReservationOfUser(userId);	
//	}
//
//	@Override
//	public List<Reservation> findAllReservations() {
//		return reservationRepository.findAll();
//	}
//
//
//} 
