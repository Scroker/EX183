package org.exam.ex183.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;
	
	public ValidationException() {};

	public ValidationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
