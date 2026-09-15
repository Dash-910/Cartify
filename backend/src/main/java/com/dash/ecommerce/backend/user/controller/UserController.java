package com.dash.ecommerce.backend.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dash.ecommerce.backend.user.entity.User;
import com.dash.ecommerce.backend.user.service.UserService;

import jakarta.validation.Valid;

import com.dash.ecommerce.backend.user.dto.UserRequest;
import com.dash.ecommerce.backend.user.dto.UserResponse;


@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController (UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request){
		UserResponse response = userService.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById (@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request){
		return ResponseEntity.ok(userService.updateUser(id, request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		
		return ResponseEntity.noContent().build();
	}
}
