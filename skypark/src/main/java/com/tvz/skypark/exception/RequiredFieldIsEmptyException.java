package com.tvz.skypark.exception;

public class RequiredFieldIsEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899098893675608707L;
	
	

	public RequiredFieldIsEmptyException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		return "Some required fields are empty!";
	}

	
	
}
