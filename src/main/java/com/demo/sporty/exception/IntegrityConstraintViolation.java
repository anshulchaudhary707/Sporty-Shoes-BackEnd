package com.demo.sporty.exception;

public class IntegrityConstraintViolation extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	public IntegrityConstraintViolation(String what, String where) {
		this.message = what+" already exists in "+where+" database.";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}