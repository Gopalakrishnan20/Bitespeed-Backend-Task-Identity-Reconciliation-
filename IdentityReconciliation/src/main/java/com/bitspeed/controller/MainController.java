package com.bitspeed.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitspeed.model.Contact;
import com.bitspeed.model.IdentifyRequest;
import com.bitspeed.model.UserSummary;
import com.bitspeed.service.ContactService;

@RestController
public class MainController {
	
	@Autowired
	ContactService contactService;
	
	@PostMapping("/identify")
	public UserSummary identifyUser(@RequestBody IdentifyRequest request) {
		return contactService.makeSummary(request.getEmail(), request.getPhoneNumber().toString());
		
	}

	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody IdentifyRequest request){
		return contactService.addContact(request);
	}

}
