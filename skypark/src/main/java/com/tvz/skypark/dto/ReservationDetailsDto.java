package com.tvz.skypark.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import com.tvz.skypark.model.Reservation;
import com.tvz.skypark.model.User;
import com.tvz.skypark.utils.ParkUtils.ParkingType;
import com.tvz.skypark.utils.ParkUtils.ReservationStatus;
import com.tvz.skypark.utils.ParkUtils.VehicleManufacturer;
import com.tvz.skypark.utils.ParkUtils.VehicleType;

public class ReservationDetailsDto implements Serializable{
	

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -3275070121052197955L;
	
	private Long id;
	private User user;
	private String vehicleModel;
	private VehicleManufacturer vehicleManufacturer;
	private VehicleType vehicleType;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private LocalDate reservationDate;
	private Double price;
	private ReservationStatus reservationStatus;
	private ParkingType parkingType;

	public ReservationDetailsDto() {
		
	}

	public ReservationDetailsDto(Reservation reservation) {
		this.id = reservation.getId();
		this.user = reservation.getUser();
		this.vehicleModel = reservation.getVehicleModel();
		this.vehicleManufacturer = reservation.getVehicleManufacturer();
		this.vehicleType = reservation.getVehicleType();
		this.dateFrom = reservation.getDateFrom();
		this.dateTo = reservation.getDateTo();
		this.reservationDate = reservation.getReservationDate();
		this.price = reservation.getPrice();
		this.reservationStatus = reservation.getReservationStatus();
		this.parkingType = reservation.getParkingType();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}


	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}


	public VehicleManufacturer getVehicleManufacturer() {
		return vehicleManufacturer;
	}


	public void setVehicleManufacturer(VehicleManufacturer vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}


	public VehicleType getVehicleType() {
		return vehicleType;
	}


	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}


	public LocalDate getDateFrom() {
		return dateFrom;
	}


	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}


	public LocalDate getDateTo() {
		return dateTo;
	}


	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}


	public LocalDate getReservationDate() {
		return reservationDate;
	}


	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}


	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public ParkingType getParkingType() {
		return parkingType;
	}

	public void setParkingType(ParkingType parkingType) {
		this.parkingType = parkingType;
	}
	

}
