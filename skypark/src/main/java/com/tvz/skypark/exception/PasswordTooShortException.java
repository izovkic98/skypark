package com.tvz.skypark.exception;

public class PasswordTooShortException extends RuntimeException {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 6818473928848115517L;

	public PasswordTooShortException() {
        super();
    }

    public PasswordTooShortException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PasswordTooShortException(final String message) {
        super(message);
    }

    public PasswordTooShortException(final Throwable cause) {
        super(cause);
    }

}
