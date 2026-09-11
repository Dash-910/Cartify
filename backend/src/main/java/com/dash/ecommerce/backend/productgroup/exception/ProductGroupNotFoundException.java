package com.dash.ecommerce.backend.productgroup.exception;


public class ProductGroupNotFoundException extends RuntimeException {
	
	
	public ProductGroupNotFoundException(Long id) {
		super("Product group not found with id: " + id);
	}
	
}
