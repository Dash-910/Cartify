package com.dash.ecommerce.backend.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.dash.ecommerce.backend.order.entity.OrderStatus;

public class OrderResponse {
	
	private Long OrderId;
	private OrderStatus status;
	private BigDecimal totalAmount;
	private OrderAddressResponse shippingAddress;
	private List<OrderItemResponse> items;
	private LocalDateTime createdAt;
	
	public OrderResponse(Long orderId, OrderStatus status, BigDecimal totalAmount, OrderAddressResponse shippingAddress,
			List<OrderItemResponse> items, LocalDateTime createdAt) {
		super();
		OrderId = orderId;
		this.status = status;
		this.totalAmount = totalAmount;
		this.shippingAddress = shippingAddress;
		this.items = items;
		this.createdAt = createdAt;
	}

	public Long getOrderId() {
		return OrderId;
	}

	public void setOrderId(Long orderId) {
		OrderId = orderId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderAddressResponse getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(OrderAddressResponse shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderItemResponse> getItems() {
		return items;
	}

	public void setItems(List<OrderItemResponse> items) {
		this.items = items;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
