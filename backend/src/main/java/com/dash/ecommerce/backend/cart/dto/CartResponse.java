package com.dash.ecommerce.backend.cart.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartResponse {
	
	private Long cartId;
	private List<CartItemResponse> items;
	private Integer totalQuantity;
	private BigDecimal totalAmount;
	
	public CartResponse() {
		
	}

	public CartResponse(Long cartId, List<CartItemResponse> items, Integer totalQuantity, BigDecimal totalAmount) {
		super();
		this.cartId = cartId;
		this.items = items;
		this.totalQuantity = totalQuantity;
		this.totalAmount = totalAmount;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public List<CartItemResponse> getItems() {
		return items;
	}

	public void setItems(List<CartItemResponse> items) {
		this.items = items;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
