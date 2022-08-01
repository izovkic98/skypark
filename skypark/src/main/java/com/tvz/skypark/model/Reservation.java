package com.tvz.skypark.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Column(name="user_id", nullable=false )	
	private Long userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn	(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false )
	private User user;
	
	@Column(name="model", length=50, nullable=false, unique=true )	
	private String vehicleModel;
	
	@Enumerated(EnumType.STRING)
	private VehicleManufacturer vehicleManufacturer;

	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;
	
	@Column(name="date_from", nullable = false )	
	private LocalDateTime dateFrom;
	
	@Column(name="date_to", nullable = false )	
	private LocalDateTime dateTo;
	
	@Column(name="purchase_date", nullable = false )	
	private LocalDateTime reservationDate;
	
	@Column(name="price", nullable = false )	
	private Double price;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus;

}
