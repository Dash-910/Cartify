package com.dash.ecommerce.backend.productgroup.exception;

public class ProductGroupInUseException extends RuntimeException {
	
	public ProductGroupInUseException(Long id) {
		super("Cannot delete product group with id " + id + " because it is being used by products");
	}
	
}
