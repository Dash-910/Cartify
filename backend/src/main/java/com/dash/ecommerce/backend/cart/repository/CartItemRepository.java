package com.dash.ecommerce.backend.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.dash.ecommerce.backend.cart.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	Optional<CartItem> findByCartIdAndProductId(Long CartId, Long ProductId);
}
