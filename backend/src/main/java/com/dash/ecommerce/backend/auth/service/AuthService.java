package com.dash.ecommerce.backend.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dash.ecommerce.backend.auth.dto.AuthResponse;
import com.dash.ecommerce.backend.auth.dto.LoginRequest;
import com.dash.ecommerce.backend.auth.dto.RegisterRequest;
import com.dash.ecommerce.backend.auth.exception.InvalidCredentialsException;
import com.dash.ecommerce.backend.auth.security.JwtService;
import com.dash.ecommerce.backend.user.entity.Role;
import com.dash.ecommerce.backend.user.entity.User;
import com.dash.ecommerce.backend.user.repository.UserRepository;

@Service
public class AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}
	
	public void register(RegisterRequest request) {
		
		User user = new User();
		
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPhoneNumber(request.getPhoneNumber());
		
		//Hashing password
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		user.setRole(Role.CUSTOMER);
		
		userRepository.save(user);
		
	}
	
	public AuthResponse login(LoginRequest request) {
		
		User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid email or password"));
		
		boolean passwordMatches = passwordEncoder.matches(request.getPassword(),user.getPassword());
		if(!passwordMatches) {
			throw new InvalidCredentialsException("Invalid email or password");
		}
		
		String token = jwtService.generateToken(user);
		
		return new AuthResponse(token,user.getId(),user.getRole().name());
				
	}
	
}
