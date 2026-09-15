package com.dash.ecommerce.backend.user.dto;

import jakarta.validation.constraints.NotBlank;

public class AddressRequest {
	
	@NotBlank 
	private String fullname;
	
	@NotBlank
	private String phoneNumber;
	
	@NotBlank
	private String addressLine1;
	
	private String addressLine2;
	
	@NotBlank
	private String city;
	
	@NotBlank 
	private String state;
	
	@NotBlank
	private String postalCode;
	
	@NotBlank
	private String country;
	
	private boolean isDefault;
	
	public AddressRequest() {
		
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	
}
