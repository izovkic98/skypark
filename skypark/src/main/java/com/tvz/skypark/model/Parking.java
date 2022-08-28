package com.tvz.skypark.model;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tvz.skypark.utils.ParkUtils.ParkingStatus;
import com.tvz.skypark.utils.ParkUtils.ParkingType;

import lombok.Data;


@Data
@Entity
@Table(name="parking")
public class Parking implements Serializable{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 3613584535055228633L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ParkingStatus parkingStatus;
	
	@Enumerated(EnumType.STRING)
	private ParkingType parkingType;
	

	public Parking() {
		super();
	}

	public Parking(Long id, ParkingStatus parkingStatus, ParkingType parkingType) {
		super();
		this.id = id;
		this.parkingStatus = parkingStatus;
		this.parkingType = parkingType;
	}

}
