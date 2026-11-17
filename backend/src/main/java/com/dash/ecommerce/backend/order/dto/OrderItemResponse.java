package com.dash.ecommerce.backend.order.dto;

import java.math.BigDecimal;

public class OrderItemResponse {
	private Long productId;
	private String productName;
	private BigDecimal unitPrice;
	private Integer quantity;
	private BigDecimal lineTotal;
	
	
	public OrderItemResponse(Long productId, String productName, BigDecimal unitPrice, Integer quantity, BigDecimal lineTotal) {
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.lineTotal = lineTotal;
	}

	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public BigDecimal getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getLineTotal() {
		return lineTotal;
	}


	public void setLineTotal(BigDecimal lineTotal) {
		this.lineTotal = lineTotal;
	}
	
}
