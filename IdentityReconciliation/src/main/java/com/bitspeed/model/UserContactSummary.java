package com.bitspeed.model;

import java.util.List;

public class UserContactSummary {
	
	private Integer primaryContactId;
	private List<String> email;
	private List<String> phoneNumbers;
	private List<Integer> secondaryContactIds;
	public Integer getPrimaryContactId() {
		return primaryContactId;
	}
	public void setPrimaryContactId(Integer primaryContactId) {
		this.primaryContactId = primaryContactId;
	}
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public List<Integer> getSecondaryContactIds() {
		return secondaryContactIds;
	}
	public void setSecondaryContactIds(List<Integer> secondaryContactIds) {
		this.secondaryContactIds = secondaryContactIds;
	}
	public UserContactSummary(Object object, List<String> list, List<String> phoneNumbers,
							  Object object2) {
		super();
		this.primaryContactId = (Integer) object;
		this.email = list;
		this.phoneNumbers = phoneNumbers;
		this.secondaryContactIds = (List<Integer>) object2;
	}
	
	public UserContactSummary() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserContactSummary [primaryContactId=" + primaryContactId + ", email=" + email + ", phoneNumbers="
				+ phoneNumbers + ", secondaryContactIds=" + secondaryContactIds + "]";
	}
	
	
}
