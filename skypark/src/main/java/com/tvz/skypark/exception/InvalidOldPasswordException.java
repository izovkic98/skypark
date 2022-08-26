package com.tvz.skypark.exception;

public class InvalidOldPasswordException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1534411274928617117L;

    public InvalidOldPasswordException() {
        super();
    }

    public InvalidOldPasswordException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidOldPasswordException(final String message) {
        super(message);
    }

    public InvalidOldPasswordException(final Throwable cause) {
        super(cause);
    }

}
