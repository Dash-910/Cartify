package com.dash.ecommerce.backend.cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dash.ecommerce.backend.cart.dto.AddCartItemRequest;
import com.dash.ecommerce.backend.cart.dto.CartResponse;
import com.dash.ecommerce.backend.cart.dto.UpdateCartItemRequest;
import com.dash.ecommerce.backend.cart.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	private final CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@PostMapping("/items")
	public ResponseEntity<CartResponse> addItem(@Valid @RequestBody AddCartItemRequest request){
		CartResponse cart = cartService.addItem(request.getProductId(), request.getQuantity());
		return ResponseEntity.ok(cart);
	}
	
	@GetMapping
	public CartResponse getCart() {
		return cartService.getCart();
	}
	
	@PutMapping("/items/{productId}")
	public CartResponse updateItem(@PathVariable Long productId, @Valid @RequestBody UpdateCartItemRequest request) {
		return cartService.updateItem(productId, request.getQuantity());
	}
	
	@DeleteMapping("/items/{productId}")
	public ResponseEntity<Void> removeItem(@PathVariable Long productId){
		cartService.removeItem(productId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> clearCart(){
		cartService.clearCart();
		return ResponseEntity.noContent().build();
	}
}
