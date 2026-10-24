package com.dash.ecommerce.backend.cart.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dash.ecommerce.backend.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
		name = "carts",
		uniqueConstraints = @UniqueConstraint(
				name = "uk_carts_user_id",
				columnNames = "user_id"
			)
		)

public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;
	
	@OneToMany(
			mappedBy = "cart",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<CartItem> items = new ArrayList<>();
	
	@Column (name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@Column (name = "updated_at", nullable = false, updatable = false)
	private LocalDateTime updatedAt;
	
	public Cart() {
		
	}
	
	public Cart(User user) {
		this.user = user;
	}
	
	public void addItem(CartItem item) {
		items.add(item);
		item.setCart(this);
	}
	
	public void removeItems(CartItem item) {
		items.remove(item);
		item.setCart(null);
	}
	
	public void clearItems() {
		for (CartItem item : new ArrayList<>(items)) {
			removeItems(item);
		}
	}
	
	/** PrePersist - tells JPA to call the method before inserting a new cart 
	 *  PreUpdate - Tells JPA to call the method before updating an existing a cart**/
	
	
	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		createdAt = now;
		updatedAt = now;
	}
	
	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<CartItem> getItems(){
		return items;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public LocalDateTime getUpdateAt() {
		return updatedAt;
	}
}
