package com.tvz.skypark.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tvz.skypark.dto.ReservationDetailsDto;
import com.tvz.skypark.utils.ParkUtils.ParkingType;
import com.tvz.skypark.utils.ParkUtils.ReservationStatus;
import com.tvz.skypark.utils.ParkUtils.VehicleManufacturer;
import com.tvz.skypark.utils.ParkUtils.VehicleType;

import lombok.Data;


@Data
@Entity
@Table(name="reservation")
public class Reservation implements Serializable{


	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -1384491574939799161L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn	(name = "user_id", nullable = true)
	private User user;
	
	@Column(name="model", length=50, nullable=false)	
	private String vehicleModel;
	
	@Enumerated(EnumType.STRING)
	private VehicleManufacturer vehicleManufacturer;

	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;
	
	@Column(name="date_from", nullable = false )	
	private LocalDate dateFrom;
	
	@Column(name="date_to", nullable = false )	
	private LocalDate dateTo;
	
	@Column(name="reservation_date", nullable = false )	
	private String reservationDate;
	
	@Column(name="price", nullable = false )	
	private Double price;
	
	@Column(name="code")	
	private String code;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus;
	
	@Enumerated(EnumType.STRING)
	private ParkingType parkingType;
	

	public Reservation() {
		super();
	}
	
	public Reservation(Long id, User user, String vehicleModel, VehicleManufacturer vehicleManufacturer,
			VehicleType vehicleType, LocalDate dateFrom, LocalDate dateTo, String reservationDate, Double price,
			String code, ReservationStatus reservationStatus, ParkingType parkingType) {
		super();
		this.id = id;
		this.user = user;
		this.vehicleModel = vehicleModel;
		this.vehicleManufacturer = vehicleManufacturer;
		this.vehicleType = vehicleType;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.reservationDate = reservationDate;
		this.price = price;
		this.code = code;
		this.reservationStatus = reservationStatus;
		this.parkingType = parkingType;
	}
	
	public Reservation(ReservationDetailsDto reservationDetailsDto) {
		this.user = reservationDetailsDto.getUser();
		this.vehicleModel = reservationDetailsDto.getVehicleModel();
		this.vehicleManufacturer = reservationDetailsDto.getVehicleManufacturer();
		this.vehicleType = reservationDetailsDto.getVehicleType();
		this.dateFrom = reservationDetailsDto.getDateFrom();
		this.dateTo = reservationDetailsDto.getDateTo();
		this.reservationDate = reservationDetailsDto.getReservationDate();
		this.price = reservationDetailsDto.getPrice();
		this.reservationStatus = ReservationStatus.IN_PROCESS;
		this.parkingType = reservationDetailsDto.getParkingType();
		this.code = reservationDetailsDto.getCode();
		
	}

}
