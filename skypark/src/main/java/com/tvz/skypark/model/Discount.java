package com.tvz.skypark.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 
 * This entity might not be neccessary
 * 
 * 
 * @author Ivan Zovkić
 *
 */
public class Discount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8991058867984605642L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer userID;
	private String discountValue;
	
	public Discount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Discount(Integer id, Integer userID, String discountValue) {
		super();
		this.id = id;
		this.userID = userID;
		this.discountValue = discountValue;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
