package com.tvz.skypark.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//@Data
//@Entity
//@Table(name="invoices")
public class Invoice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6478679638002106452L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="customerName", length=50, nullable=false, unique=true )	
	private String customerName;
	@Column(name="customerLastName", length=50, nullable=false, unique=true )	
	private String customerLastName;
	@Column(name="customerPhoneNumber", length=50, nullable=false, unique=true )	
	private String customerPhoneNumber;
	@Column(name="customerEmail", length=50, nullable=false, unique=true )	
	private String customerEmail;
	@Column(name="customerCity", length=50, nullable=false, unique=true )	
	private String customerCity;
	@Column(name="customerStreet", length=50, nullable=false, unique=true )	
	private String customerStreet;
	@Column(name="customerStreetNumber", length=50, nullable=false, unique=true )	
	private String customerStreetNumber;
	 
	@Column(name="issuerName", length=50, nullable=false, unique=true )	
	private String issuerName;
	@Column(name="issuerLastName", length=50, nullable=false, unique=true )	
	private String issuerLastName;
	@Column(name="issuerPhoneNumber", length=50, nullable=false, unique=true )	
	private String issuerPhoneNumber;
	@Column(name="issuerEmail", length=50, nullable=false, unique=true )	
	private String issuerEmail;
	@Column(name="issuerEmail", length=50, nullable=false, unique=true )	
	private String issuerCity;
	@Column(name="issuerStreet", length=50, nullable=false, unique=true )	
	private String issuerStreet;
	@Column(name="issuerStreetNumber", length=50, nullable=false, unique=true )	
	private String issuerStreetNumber;
	
	
	@Column(name="discount", length=100, nullable=false, unique=true )
	private String discount;
	@Column(name="price", length=100, nullable=false, unique=true )
	private String price;
	@Column(name="dateOfIssue", length=100, nullable=false, unique=true )
	private LocalDate dateOfIssue;
	
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoice(Long id, String customerName, String customerLastName, String customerPhoneNumber,
			String customerEmail, String customerCity, String customerStreet, String customerStreetNumber,
			String issuerName, String issuerLastName, String issuerPhoneNumber, String issuerEmail, String issuerCity,
			String issuerStreet, String issuerStreetNumber, String discount, String price, LocalDate dateOfIssue) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerLastName = customerLastName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerEmail = customerEmail;
		this.customerCity = customerCity;
		this.customerStreet = customerStreet;
		this.customerStreetNumber = customerStreetNumber;
		this.issuerName = issuerName;
		this.issuerLastName = issuerLastName;
		this.issuerPhoneNumber = issuerPhoneNumber;
		this.issuerEmail = issuerEmail;
		this.issuerCity = issuerCity;
		this.issuerStreet = issuerStreet;
		this.issuerStreetNumber = issuerStreetNumber;
		this.discount = discount;
		this.price = price;
		this.dateOfIssue = dateOfIssue;
	}
	

}
