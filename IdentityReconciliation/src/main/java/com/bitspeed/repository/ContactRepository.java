package com.bitspeed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitspeed.model.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	List<Contact> findByEmail(String email);

	List<Contact> findByPhoneNumber(String phoneNumber);

}
