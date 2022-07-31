package com.tvz.skypark.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tvz.skypark.utils.ParkUtils.VehicleManufacturer;
import com.tvz.skypark.utils.ParkUtils.VehicleType;

import lombok.Data;


@Data
@Entity
@Table(name="vehicle")
public class Vehicle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2275070202804654306L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="user_id", nullable=false, insertable = false, updatable = false)	
	private Long userId;
	
	@Column(name="user_id", nullable=false)	
	private User user;

	@Column(name="model", length=50, nullable=false, unique=true )	
	private String model;
	
	@Column(name="create_time", length=100, nullable=false, unique=true )
	private LocalDateTime createTime;
	
	@Enumerated(EnumType.STRING)
	private VehicleManufacturer manufacturer;

	@Enumerated(EnumType.STRING)
	private VehicleType type;
	

}
