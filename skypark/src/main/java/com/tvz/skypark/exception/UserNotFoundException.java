package com.tvz.skypark.exception;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6387054072305237020L;

	public UserNotFoundException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "User not found in database!";
	}
	
	
	
	

}
