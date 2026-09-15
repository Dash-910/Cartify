package com.dash.ecommerce.backend.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.dash.ecommerce.backend.user.dto.AddressRequest;
import com.dash.ecommerce.backend.user.dto.AddressResponse;
import com.dash.ecommerce.backend.user.entity.Address;
import com.dash.ecommerce.backend.user.service.AddressService;

@RestController
@RequestMapping("/users/{userId}/addresses")
public class AddressController {
	
	private final AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@PostMapping
	public ResponseEntity<AddressResponse> createAddress(@PathVariable Long userId, @Valid @RequestBody AddressRequest request){
		AddressResponse response = addressService.createAddress(userId, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<AddressResponse>> getAddressByUserId(@PathVariable Long userId){
		return ResponseEntity.ok(addressService.getAddressesByUserId(userId));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long userId, @PathVariable Long id){
		return ResponseEntity.ok(addressService.getAddressById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long userId, @PathVariable Long id, @Valid @RequestBody AddressRequest request){
		return ResponseEntity.ok(addressService.updateAddress(id,request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable Long userId, @PathVariable Long id){
		addressService.deleteAddress(id);
		return ResponseEntity.noContent().build();
	}
}
