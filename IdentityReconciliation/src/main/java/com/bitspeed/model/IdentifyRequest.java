package com.bitspeed.model;

import jakarta.annotation.Nullable;

public class IdentifyRequest {
	@Nullable
	private String email;
	@Nullable
	private String phoneNumber;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public IdentifyRequest(String email, String phoneNumber) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public IdentifyRequest() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "IdentifyRequest [email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	
	
	

}
