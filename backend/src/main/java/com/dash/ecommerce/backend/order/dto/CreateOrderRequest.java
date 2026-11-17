package com.dash.ecommerce.backend.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateOrderRequest {
	
	@NotNull(message = "Address ID is required")
	@Positive(message = "Address ID must be positive")
	private Long addressId;
	
	
	public CreateOrderRequest() {
		
	}
	
	public Long getAddressId() {
		return addressId;
	}
	
	public void setAddress(Long addressId) {
		this.addressId = addressId;
	}
	
}
