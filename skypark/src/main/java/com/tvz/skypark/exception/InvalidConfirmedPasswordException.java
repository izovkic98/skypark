package com.tvz.skypark.exception;

public class InvalidConfirmedPasswordException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6936855571213227050L;

	public InvalidConfirmedPasswordException() {
        super();
    }

    public InvalidConfirmedPasswordException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidConfirmedPasswordException(final String message) {
        super(message);
    }

    public InvalidConfirmedPasswordException(final Throwable cause) {
        super(cause);
    }

}
