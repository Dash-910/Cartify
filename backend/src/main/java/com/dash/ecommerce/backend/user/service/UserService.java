package com.dash.ecommerce.backend.user.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.dash.ecommerce.backend.user.dto.UserRequest;
import com.dash.ecommerce.backend.user.dto.UserResponse;
import com.dash.ecommerce.backend.user.entity.Role;
import com.dash.ecommerce.backend.user.entity.User;
import com.dash.ecommerce.backend.user.exception.UserNotFoundException;
import com.dash.ecommerce.backend.user.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserResponse createUser (UserRequest userRequest) {
		
		User user = new User();
		
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setPhoneNumber(userRequest.getPhoneNumber());
		user.setPassword(userRequest.getPassword());
		user.setRole(Role.CUSTOMER);
		
		User savedUser = userRepository.save(user);
		return toResponse(savedUser);
	}
	
	public UserResponse getUserById (Long id) {
		User user =  userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		return toResponse(user);
	}
	
	public List<UserResponse> getAllUsers(){
		return userRepository.findAll().stream().map(this::toResponse).toList();
	}
	
	public UserResponse updateUser (Long id, UserRequest userRequest) {
		User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setPassword(userRequest.getPassword());
        
        User updatedUser = userRepository.save(user);

        return toResponse(updatedUser);
	}
	
	public void deleteUser (Long id) {
		User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		
		userRepository.delete(user);
	}
	
	private UserResponse toResponse(User user) {
		return new UserResponse(
				  user.getId(),
				  user.getName(),
				  user.getEmail(),
				  user.getPhoneNumber(),
				  user.getRole()
				);
	}
	
}
