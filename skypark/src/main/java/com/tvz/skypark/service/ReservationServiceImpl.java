package com.tvz.skypark.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvz.skypark.dto.ReservationDetailsDto;
import com.tvz.skypark.exception.FullParkingException;
import com.tvz.skypark.exception.ReservationDateIsIncorrectException;
import com.tvz.skypark.exception.ReservationNotFoundException;
import com.tvz.skypark.model.Parking;
import com.tvz.skypark.model.Reservation;
import com.tvz.skypark.model.User;
import com.tvz.skypark.repository.ParkingRepository;
import com.tvz.skypark.repository.ReservationRepository;
import com.tvz.skypark.repository.UserRepository;
import com.tvz.skypark.utils.LocalDateTimeComparator;
import com.tvz.skypark.utils.ParkUtils.ParkingStatus;
import com.tvz.skypark.utils.ParkUtils.ParkingType;
import com.tvz.skypark.utils.ParkUtils.ReservationStatus;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private final ReservationRepository reservationRepository;

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final ParkingRepository parkingRepository;

	@Autowired
	private final ParkingService parkingService;

	@Autowired
	PrivilegeService privilegeService;

	public ReservationServiceImpl(ReservationRepository reservationRepository, ParkingRepository parkingRepository,
			ParkingService parkingService, UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.parkingRepository = parkingRepository;
		this.parkingService = parkingService;
		this.userRepository = userRepository;

	}

	@Override
	public ReservationDetailsDto saveReservation(ReservationDetailsDto reservationDetailsDto)
			throws ReservationDateIsIncorrectException {

		boolean userFlag = reservationDetailsDto.getUser() != null;

		if (reservationDetailsDto.getDateFrom().isAfter(reservationDetailsDto.getDateTo())) {
			throw new ReservationDateIsIncorrectException("Date from is after date to which is wrong!");
		}

		if (userFlag) {
			User user = userRepository.findByIdLike(reservationDetailsDto.getUser().getId());

			// Loyalty points i doscount logika
			privilegeService.loyaltyPoints(user, reservationDetailsDto.getPrice().floatValue());
		}
		
		reservationDetailsDto
						.setReservationDate(LocalDateTime.now()
						.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		return new ReservationDetailsDto(reservationRepository.save(new Reservation(reservationDetailsDto)));
	}

	@Override
	public List<ReservationDetailsDto> findAllReservations() {
		return reservationRepository.findAll().stream().map(ReservationDetailsDto::new).collect(Collectors.toList());
	}

	@Override
	public List<ReservationDetailsDto> findAllReservationsOfUser(Long userId) {

		Comparator<LocalDateTime> comparator = new LocalDateTimeComparator();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return reservationRepository.findByUser_IdLike(userId).stream().map(ReservationDetailsDto::new)
				.sorted((item1, item2) -> comparator.compare(LocalDateTime.parse(item1.getReservationDate(), formatter),
						LocalDateTime.parse(item2.getReservationDate(), formatter)))
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
	public ReservationDetailsDto updateReservation(ReservationDetailsDto updatedReservation)
			throws ReservationDateIsIncorrectException, FullParkingException {

		Reservation reservation = reservationRepository.findByIdLike(updatedReservation.getId());

		if (updatedReservation.getDateFrom().isAfter(updatedReservation.getDateTo())) {
			throw new ReservationDateIsIncorrectException("Date from is after date to which is wrong!");
		}

		reservation.setReservationStatus(updatedReservation.getReservationStatus());
		reservation.setVehicleModel(updatedReservation.getVehicleModel());
		reservation.setVehicleManufacturer(updatedReservation.getVehicleManufacturer());
		reservation.setVehicleType(updatedReservation.getVehicleType());
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

			// logika u vezi micanja parking mjesta po zonama

			if (reservation.getParkingType().equals(ParkingType.I_ZONE)
					&& reservation.getReservationStatus().equals(ReservationStatus.APPROVED)) {
				Parking parking = parkingService.findAllOccupiedParkingsFirstZone().stream().findFirst().get();
				parking.setParkingStatus(ParkingStatus.Free);
				parkingRepository.save(parking);

			} else if (reservation.getParkingType().equals(ParkingType.II_ZONE)
					&& reservation.getReservationStatus().equals(ReservationStatus.APPROVED)) {
				Parking parking = parkingService.findAllOccupiedParkingsSecondZone().stream().findFirst().get();
				parking.setParkingStatus(ParkingStatus.Free);
				parkingRepository.save(parking);

			}

			reservationRepository.deleteById(reservationId);
		} else {
			throw new ReservationNotFoundException("Reservation under id:" + reservationId + " is not found.");
		}
	}

	@Override
	public ReservationDetailsDto changeReservationStatus(ReservationDetailsDto updatedReservation)
			throws ReservationDateIsIncorrectException, FullParkingException {

		Reservation reservation = reservationRepository.findByIdLike(updatedReservation.getId());

		if (updatedReservation.getReservationStatus().equals(ReservationStatus.APPROVED)) {

			// logika u vezi micanja parking mjesta po zonama
			if (updatedReservation.getParkingType().equals(ParkingType.I_ZONE)) {
				Parking parking = parkingService.findAllFreeParkingsFirstZone().stream().findFirst()
						.orElseThrow(() -> new FullParkingException("Parking is full!"));
				parking.setParkingStatus(ParkingStatus.Occupied);
				parkingRepository.save(parking);

			} else {
				Parking parking = parkingService.findAllFreeParkingsSecondZone().stream().findFirst()
						.orElseThrow(() -> new FullParkingException("Parking is full!"));
				parking.setParkingStatus(ParkingStatus.Occupied);
				parkingRepository.save(parking);

			}

		} else if (updatedReservation.getReservationStatus().equals(ReservationStatus.IN_PROCESS)) {
			// logika u vezi micanja parking mjesta po zonama

			if (updatedReservation.getParkingType().equals(ParkingType.I_ZONE)) {
				Parking parking = parkingService.findAllOccupiedParkingsFirstZone().stream().findFirst().get();

				parking.setParkingStatus(ParkingStatus.Free);
				parkingRepository.save(parking);

			} else {
				Parking parking = parkingService.findAllOccupiedParkingsSecondZone().stream().findFirst().get();

				parking.setParkingStatus(ParkingStatus.Free);
				parkingRepository.save(parking);

			}

		}

		reservation.setReservationStatus(updatedReservation.getReservationStatus());
		reservationRepository.save(reservation);

		return updatedReservation;
	}

	@Override
	public List<ReservationDetailsDto> findAllCurrentReservations(Long userId) {

		List<ReservationDetailsDto> inBetween = reservationRepository.findByUser_IdLike(userId).stream()
				.map(ReservationDetailsDto::new)
				.filter(reservation -> LocalDate.now().isBefore(reservation.getDateTo()))
				.filter(reservation -> LocalDate.now().isAfter(reservation.getDateFrom())).collect(Collectors.toList());

		List<ReservationDetailsDto> current = reservationRepository.findByUser_IdLike(userId).stream()
				.map(ReservationDetailsDto::new).filter(reservation -> (LocalDate.now().equals(reservation.getDateTo())
						|| LocalDate.now().equals(reservation.getDateFrom())))
				.collect(Collectors.toList());

		List<ReservationDetailsDto> newList = Stream.concat(inBetween.stream(), current.stream()).toList();

		return newList;

	}

}
