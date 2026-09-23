package com.dash.ecommerce.backend.auth.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dash.ecommerce.backend.auth.dto.AuthResponse;
import com.dash.ecommerce.backend.auth.dto.LoginRequest;
import com.dash.ecommerce.backend.auth.dto.RegisterRequest;
import com.dash.ecommerce.backend.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request){
		authService.register(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("User registered sucessfully.");
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
		
		AuthResponse response = authService.login(request);
		return ResponseEntity.ok(response);
	}
}