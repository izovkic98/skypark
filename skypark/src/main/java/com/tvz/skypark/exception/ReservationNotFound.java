package com.tvz.skypark.exception;

public class ReservationNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6387054072305237020L;

	public ReservationNotFound(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "Reservation not found in database!";
	}
	
	
	
	

}
