package com.tvz.skypark.exception;

public class UsernameOrEmailAreAlreadyTakenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3860775050601140351L;

	public UsernameOrEmailAreAlreadyTakenException(String message) {
		super(message);

	}

	@Override
	public String getMessage() {
		return "Username and email are already taken!";
	}
	
	

}
