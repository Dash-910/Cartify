package com.dash.ecommerce.backend.cart.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dash.ecommerce.backend.auth.exception.AccessDeniedException;
import com.dash.ecommerce.backend.cart.dto.CartItemResponse;
import com.dash.ecommerce.backend.cart.dto.CartResponse;
import com.dash.ecommerce.backend.cart.entity.Cart;
import com.dash.ecommerce.backend.cart.entity.CartItem;
import com.dash.ecommerce.backend.cart.exception.CartItemNotFoundException;
import com.dash.ecommerce.backend.cart.exception.CartNotFoundException;
import com.dash.ecommerce.backend.cart.exception.InsufficientStockException;
import com.dash.ecommerce.backend.cart.repository.CartItemRepository;
import com.dash.ecommerce.backend.cart.repository.CartRepository;
import com.dash.ecommerce.backend.product.entity.Product;
import com.dash.ecommerce.backend.product.exception.ProductNotFoundException;
import com.dash.ecommerce.backend.product.repository.ProductRepository;
import com.dash.ecommerce.backend.user.entity.User;
import com.dash.ecommerce.backend.user.repository.UserRepository;

@Service
public class CartService {
	
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	
	public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, UserRepository userRepository ) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public CartResponse addItem(Long productId, Integer quantity) {
		User user = getAuthenticatedUser();
		
		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException(productId));
		
		Cart cart = cartRepository.findByUserId(user.getId()).orElseGet(()->cartRepository.save(new Cart(user)));
		
		CartItem item = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId).orElse(null); 
		
		int updatedQuantity = quantity;
		if(item != null) {
			updatedQuantity = Math.addExact(item.getQuantity(), quantity);
		}
		checkStock(product, updatedQuantity);	
		
		if(item == null) {
			item = new CartItem(product,quantity);
			cart.addItem(item);
		} else {
			item.setQuantity(updatedQuantity);
		}
		
		cartRepository.save(cart);
		return toResponse(cart);
		
	}
	
	@Transactional(readOnly = true)
	public CartResponse getCart() {
		User user = getAuthenticatedUser();
		
		Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(()-> new CartNotFoundException(user.getId()));
		
		return toResponse(cart);
		
	}
	
	@Transactional
	public CartResponse updateItem(Long productId, Integer quantity) {
		User user = getAuthenticatedUser();
		
		Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(()-> new CartNotFoundException(user.getId()));
		
		CartItem item = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId).orElseThrow(()-> new CartItemNotFoundException(productId));
		
		checkStock(item.getProduct(),quantity);
		item.setQuantity(quantity);
		
		return toResponse(cart);
		
	}
	
	@Transactional
	public void removeItem(Long productId) {
		User user = getAuthenticatedUser();
		
		Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(()-> new CartNotFoundException(user.getId()));
		
		CartItem item = cartItemRepository.findByCartIdAndProductId(cart.getId(),productId).orElseThrow(()-> new CartItemNotFoundException(productId));
		
		cart.removeItems(item);
	}
	
	@Transactional
	public void clearCart() {
		User user = getAuthenticatedUser();
		
		Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(()-> new CartNotFoundException(user.getId()));
		
		cart.clearItems();
	}
	
	private User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal()== null || "anonymousUser".equals(authentication.getPrincipal())) {
			throw new AccessDeniedException("Authentication is required");
		}
		
		String email = authentication.getName();
		
		return userRepository.findByEmail(email).orElseThrow(()-> new AccessDeniedException("Authenticated user was not found"));
		
	}
	
	private void checkStock(Product product, int requestedQuantity) {
		if(product.getStock() == null || requestedQuantity > product.getStock()) {
			int available = product.getStock() == null ? 0 : product.getStock();
			throw new InsufficientStockException("Requested quantity exceeds available stock: " + available);
		}
	}
	
	private CartResponse toResponse(Cart cart) {
		List<CartItemResponse> items = cart.getItems().stream().map(this::toItemResponse).toList();
		
		int totalQuantity = cart.getItems().stream().mapToInt(CartItem::getQuantity).sum();
		
		BigDecimal totalAmount = items.stream().map(CartItemResponse::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return new CartResponse(cart.getId(), items, totalQuantity, totalAmount);
	}
	
	private CartItemResponse toItemResponse(CartItem item) {
		Product product = item.getProduct();
		BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
		
		return new CartItemResponse(product.getId(), product.getName(), product.getPrice(), item.getQuantity(), subtotal);
		
	}
	
}
