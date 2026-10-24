package com.dash.ecommerce.backend.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UpdateCartItemRequest {
	
	@NotNull(message = "Quantity is required")
	@Min(value = 1, message = "Quantity must be atleast 1")
	private Integer quantity;
	
	public UpdateCartItemRequest() {
		
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQauntity (Integer quantity) {
		this.quantity = quantity;
	}
}
