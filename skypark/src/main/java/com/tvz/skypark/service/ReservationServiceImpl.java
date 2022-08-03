package com.tvz.skypark.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tvz.skypark.dto.ReservationDetailsDto;
import com.tvz.skypark.model.Reservation;
import com.tvz.skypark.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{
	

	private final ReservationRepository reservationRepository;

	public ReservationServiceImpl(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public ReservationDetailsDto saveReservation(ReservationDetailsDto reservationDetailsDto) {

		reservationDetailsDto.setReservationDate(LocalDate.now());
        
        return new ReservationDetailsDto(reservationRepository.save(new Reservation(reservationDetailsDto)));
	}

	@Override
	public List<ReservationDetailsDto> findAllReservations() {
		return reservationRepository.findAll().stream().map(ReservationDetailsDto::new).collect(Collectors.toList());
	}

	@Override
	public List<ReservationDetailsDto> findAllReservationsOfUser(Long userId) {		
		return reservationRepository.findByUser_IdLike(userId).stream().map(ReservationDetailsDto::new).collect(Collectors.toList());
	}

	@Override
	public List<ReservationDetailsDto> getReservationsByUsername(String username ) {
		
        List<Reservation> reservations = reservationRepository.findByUser_UsernameLike(username);
        List<ReservationDetailsDto> reservationList = new ArrayList<>();
        if(!reservations.isEmpty()) {
        	for(Reservation r : reservations) {
        		reservationList.add(new ReservationDetailsDto(r));
        	}
            return reservationList;
        }
        else
            return new ArrayList<>();
	}



} 
