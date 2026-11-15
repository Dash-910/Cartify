package com.dash.ecommerce.backend.cart.exception;

public class CartItemNotFoundException extends RuntimeException {
	
	public CartItemNotFoundException(Long productId) {
		super("Product with id " + productId + " was not found in the cart");
	}
	
}
