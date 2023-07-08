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

import java.net.http.HttpClient;

@RestController
public class MainController {
	
	@Autowired
	ContactService contactService;
	
	@PostMapping("/identify")
	public ResponseEntity<String> identifyUser(@RequestBody IdentifyRequest request) {

		if(contactService.isContactExist(request)) {
			if (request.getPhoneNumber() == null) {
				return ResponseEntity.ok(contactService.createSummaryOnlyWithEmail(request.getEmail()).toString());
			} else if (request.getEmail() == null)
				return ResponseEntity.ok(contactService.createSummaryOnlyWithNumber(request.getPhoneNumber()).toString());

			return ResponseEntity.ok(contactService.createSummaryOnlyWithNumber(request.getPhoneNumber()).toString());
		}
		else{
			return addUser(request);
		}

	}

	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody IdentifyRequest request){
		return contactService.addContact(request);
	}

}
