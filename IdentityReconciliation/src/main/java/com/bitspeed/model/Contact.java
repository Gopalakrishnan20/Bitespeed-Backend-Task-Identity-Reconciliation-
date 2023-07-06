package com.bitspeed.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NonNull;


@Entity
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private @NonNull Integer id;
	private String phoneNumber;
	private String email;
	private Integer linkedId;
	private @NonNull String linkPrecedence;
	private @NonNull LocalDateTime createdAt;
	private @NonNull LocalDateTime updatedAt;
	private LocalDateTime deletedAt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getLinkedId() {
		return linkedId;
	}
	public void setLinkedId(Integer linkedId) {
		this.linkedId = linkedId;
	}
	public String getLinkPrecedence() {
		return linkPrecedence;
	}
	public void setLinkPrecedence(String linkPrecedence) {
		this.linkPrecedence = linkPrecedence;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
	public Contact(@NonNull Integer id, String phoneNumber, String email, Integer linkedId,
			@NonNull String linkPrecedence, @NonNull LocalDateTime createdAt, @NonNull LocalDateTime updatedAt,
			LocalDateTime deletedAt) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.linkedId = linkedId;
		this.linkPrecedence = linkPrecedence;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}
	public Contact() {
		super();
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + email + ", linkedId=" + linkedId
				+ ", linkPrecedence=" + linkPrecedence + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", deletedAt=" + deletedAt + "]";
	}
	
	
	

}
