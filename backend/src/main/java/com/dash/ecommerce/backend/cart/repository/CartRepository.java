package com.dash.ecommerce.backend.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dash.ecommerce.backend.cart.entity.Cart;

public interface CartRepository extends JpaRepository <Cart, Long> {
	
	Optional<Cart> findByUserId(Long userId);
	
}
