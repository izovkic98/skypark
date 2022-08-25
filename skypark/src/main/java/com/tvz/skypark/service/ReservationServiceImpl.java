package com.tvz.skypark.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tvz.skypark.dto.ReservationDetailsDto;
import com.tvz.skypark.exception.ReservationDateIsIncorrectException;
import com.tvz.skypark.exception.ReservationNotFoundException;
import com.tvz.skypark.model.Reservation;
import com.tvz.skypark.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;

	public ReservationServiceImpl(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public ReservationDetailsDto saveReservation(ReservationDetailsDto reservationDetailsDto) throws ReservationDateIsIncorrectException {

		reservationDetailsDto.setReservationDate(LocalDate.now());
		
		if(reservationDetailsDto.getDateFrom().isAfter(reservationDetailsDto.getDateTo())) {
			throw new ReservationDateIsIncorrectException("Date from is after date to which is wrong!");
		}
			
		return new ReservationDetailsDto(reservationRepository.save(new Reservation(reservationDetailsDto)));
	}

	@Override
	public List<ReservationDetailsDto> findAllReservations() {
		return reservationRepository.findAll().stream().map(ReservationDetailsDto::new).collect(Collectors.toList());
	}

	@Override
	public List<ReservationDetailsDto> findAllReservationsOfUser(Long userId) {
		return reservationRepository.findByUser_IdLike(userId).stream().map(ReservationDetailsDto::new)
				.collect(Collectors.toList());
	}

	@Override
	public List<ReservationDetailsDto> getReservationsByUsername(String username) {

		List<Reservation> reservations = reservationRepository.findByUser_UsernameLike(username);
		List<ReservationDetailsDto> reservationList = new ArrayList<>();
		if (!reservations.isEmpty()) {
			for (Reservation r : reservations) {
				reservationList.add(new ReservationDetailsDto(r));
			}
			return reservationList;
		} else
			return new ArrayList<>();
	}

	@Override
	public ReservationDetailsDto updateReservation(ReservationDetailsDto updatedReservation) throws ReservationDateIsIncorrectException {
		Reservation reservation = reservationRepository.findByIdLike(updatedReservation.getId());
		reservation.setReservationStatus(updatedReservation.getReservationStatus());
		reservation.setVehicleModel(updatedReservation.getVehicleModel());
		reservation.setVehicleManufacturer(updatedReservation.getVehicleManufacturer());
		reservation.setVehicleType(updatedReservation.getVehicleType());
		
		if(updatedReservation.getDateFrom().isAfter(updatedReservation.getDateTo())) {
			throw new ReservationDateIsIncorrectException("Date from is after date to which is wrong!");
		}
		
		reservation.setDateFrom(updatedReservation.getDateFrom());
		reservation.setDateTo(updatedReservation.getDateTo());
		reservation.setPrice(updatedReservation.getPrice());

		reservationRepository.save(reservation);
		return updatedReservation;
	}

	@Override
	public ReservationDetailsDto getReservationById(Long id) {
		return new ReservationDetailsDto(reservationRepository.findById(id).get());
	}

	@Override
	public void deleteReservation(Long reservationId) throws ReservationNotFoundException {
		Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
		if (reservation != null) {
			reservationRepository.deleteById(reservationId);
		} else {
			throw new ReservationNotFoundException("Reservation under id:" + reservationId + " is not found.");
		}
	}


}
