package com.tvz.skypark.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tvz.skypark.utils.ParkUtils.Tier;

import lombok.Data;
@Data
@Entity
@Table(name = "discounts")
public class Discount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -333769442740477906L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "loyalty_points", length = 100)
	private Integer loyaltyPoints;
	
	@Column(name = "code", length = 100)
	private String code;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tier", length = 100)
	private Tier tier;
	
	@OneToOne()
	@JoinColumn	(name = "user_id", nullable = false)
	private User user;

	public Discount() {
		super();
	}
		
	public Discount(User user) {
		this.user = user;
	}

	public Discount(Long id, Integer loyaltyPoints, String code, Tier tier, User user) {
		super();
		this.id = id;
		this.loyaltyPoints = loyaltyPoints;
		this.code = code;
		this.tier = tier;
		this.user = user;
	}
	
}
