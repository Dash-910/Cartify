package com.dash.ecommerce.backend.user.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.dash.ecommerce.backend.user.dto.AddressRequest;
import com.dash.ecommerce.backend.user.dto.AddressResponse;
import com.dash.ecommerce.backend.user.entity.Address;
import com.dash.ecommerce.backend.user.entity.User;
import com.dash.ecommerce.backend.user.exception.UserNotFoundException;
import com.dash.ecommerce.backend.user.repository.AddressRepository;
import com.dash.ecommerce.backend.user.repository.UserRepository;

@Service
public class AddressService {
	
	private final AddressRepository addressRepository;
	private final UserRepository userRepository;
	
	public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
	}
	
	public AddressResponse createAddress (Long userId, AddressRequest addressRequest) {
		User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
		
		Address address = new Address();
		address.setFullName(addressRequest.getFullname());
        address.setPhoneNumber(addressRequest.getPhoneNumber());
        address.setAddressLine1(addressRequest.getAddressLine1());
        address.setAddressLine2(addressRequest.getAddressLine2());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCountry(addressRequest.getCountry());
        address.setDefault(addressRequest.isDefault());
		
        address.setUser(user);
        
        Address savedAddress = addressRepository.save(address);
		return toResponse(savedAddress);
	}
	
	
	public List<AddressResponse> getAddressesByUserId(Long userId){
		userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
		
		return addressRepository.findByUserId(userId).stream().map(this::toResponse).toList();
	}
	
	public AddressResponse getAddressById(Long id) {
		Address address = addressRepository.findById(id).orElseThrow(()-> new RuntimeException("Address not found with id: " + id));
		
		return toResponse(address);
	}
	
	public AddressResponse updateAddress(Long id, AddressRequest addressRequest) {
		Address address = addressRepository.findById(id).orElseThrow(()-> new RuntimeException("Address not found with id: " + id));
		
		address.setFullName(addressRequest.getFullname());
        address.setPhoneNumber(addressRequest.getPhoneNumber());
        address.setAddressLine1(addressRequest.getAddressLine1());
        address.setAddressLine2(addressRequest.getAddressLine2());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCountry(addressRequest.getCountry());
        address.setDefault(addressRequest.isDefault());
        
        Address updatedAddress = addressRepository.save(address);
        
        return toResponse(updatedAddress);
	}
	
	public void deleteAddress (Long id) {
		Address address = addressRepository.findById(id).orElseThrow(()-> new RuntimeException("Address not found with id: " + id));
		addressRepository.delete(address);
	}
	
	public AddressResponse toResponse (Address address) {
		return new AddressResponse( address.getId(),
                address.getFullName(),
                address.getPhoneNumber(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getState(),
                address.getPostalCode(),
                address.getCountry(),
                address.isDefault()
         );
				
	}
}
