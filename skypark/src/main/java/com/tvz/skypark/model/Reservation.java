package com.tvz.skypark.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="reservation")
public class Reservation implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1384491574939799161L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="user_id", nullable=false )	
	private Long userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn	(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false )
	private User user;
	
	@Column(name="vehicle_id", length=50, nullable=false, unique=true )	
	private Long vehicleId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vehicle_id", referencedColumnName = "id",  insertable = false, updatable = false )	
	private Vehicle vehicle;
	
	@Column(name="date_from", nullable = false )	
	private LocalDateTime dateFrom;
	
	@Column(name="date_to", nullable = false )	
	private LocalDateTime dateTo;
	
	@Column(name="purchase_date", nullable = false )	
	private LocalDateTime reservationDate;
	
	@Column(name="price", nullable = false )	
	private Double price;
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(Long id, Long userId, User user, Long vehicleId, Vehicle vehicle, LocalDateTime dateFrom,
			LocalDateTime dateTo, LocalDateTime purchaseDate, Double price) {
		super();
		this.id = id;
		this.userId = userId;
		this.user = user;
		this.vehicleId = vehicleId;
		this.vehicle = vehicle;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.reservationDate = purchaseDate;
		this.price = price;
	}


}
