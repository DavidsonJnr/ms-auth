package com.ms.auth.exception;

public class AppRequestException extends RuntimeException {

	private static final long serialVersionUID = -774061250147507636L;
	
	public AppRequestException(String message) {
		super(message);
	}
	
	public AppRequestException(String message, Throwable cause) {
		super(message, cause);
	}

}
