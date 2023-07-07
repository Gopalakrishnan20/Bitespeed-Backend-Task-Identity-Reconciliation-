package com.bitspeed.controller;

import com.bitspeed.model.UserContactSummary;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitspeed.model.IdentifyRequest;
import com.bitspeed.service.ContactService;

@RestController
public class MainController {
	
	@Autowired
	ContactService contactService;
	
	@PostMapping("/identify")
	public UserContactSummary identifyUser(@RequestBody IdentifyRequest request) {
		if(request.getPhoneNumber() == null){
			return contactService.createSummaryOnlyWithEmail(request.getEmail());
		}
		else if(request.getEmail() == null)
			return contactService.createSummaryOnlyWithNumber(request.getPhoneNumber());

		return contactService.createSummaryOnlyWithNumber(request.getPhoneNumber());
	}

	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody IdentifyRequest request){
		return contactService.addContact(request);
	}

}
