package com.bitspeed.model;

import java.util.List;

public class UserSummary {
	
	private Integer primaryContactId;
	private List<Object> email;
	private List<String> phoneNumbers;
	private List<Integer> secondaryContactIds;
	public Integer getPrimaryContactId() {
		return primaryContactId;
	}
	public void setPrimaryContactId(Integer primaryContactId) {
		this.primaryContactId = primaryContactId;
	}
	public List<Object> getEmail() {
		return email;
	}
	public void setEmail(List<Object> email) {
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
	public UserSummary(Object object, List<Object> list, List<String> phoneNumbers,
			Object object2) {
		super();
		this.primaryContactId = (Integer) object;
		this.email = list;
		this.phoneNumbers = phoneNumbers;
		this.secondaryContactIds = (List<Integer>) object2;
	}
	
	public UserSummary() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserSummary [primaryContactId=" + primaryContactId + ", email=" + email + ", phoneNumbers="
				+ phoneNumbers + ", secondaryContactIds=" + secondaryContactIds + "]";
	}
	
	
}
