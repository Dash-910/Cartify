package com.dash.ecommerce.backend.cart.exception;

public class CartNotFoundException extends RuntimeException {
	
	public CartNotFoundException (Long userId) {
		super("Cart not found for user with id: " + userId);
	}
	
}
