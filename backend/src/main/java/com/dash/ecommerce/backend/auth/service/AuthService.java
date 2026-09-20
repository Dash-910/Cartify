package com.dash.ecommerce.backend.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dash.ecommerce.backend.auth.dto.RegisterRequest;
import com.dash.ecommerce.backend.user.entity.Role;
import com.dash.ecommerce.backend.user.entity.User;
import com.dash.ecommerce.backend.user.repository.UserRepository;

@Service
public class AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
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
	
}
