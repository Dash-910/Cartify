package com.dash.ecommerce.backend.auth.exception;

public class EmailAlreadyExistsException  extends RuntimeException {
	
	public EmailAlreadyExistsException(String message) {
		super(message);
	}
	
}
