package com.dash.ecommerce.backend.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dash.ecommerce.backend.order.entity.Order;

public interface OrderRepository extends JpaRepository <Order, Long> {
	
	List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);
	
	Optional<Order> findByIdAndUserId(Long orderId, Long userId);
}
