package com.bitspeed.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.bitspeed.model.IdentifyRequest;
import com.bitspeed.model.UserContactSummary;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitspeed.model.Contact;
import com.bitspeed.repository.ContactRepository;

@Service
public class ContactService {
	
	
	@Autowired
	ContactRepository contactRepository;

	public ResponseEntity<String> addContact(IdentifyRequest request){
		List<Contact> eContacts = contactRepository.findByEmail(request.getEmail());
		List<Contact> pContacts = contactRepository.findByPhoneNumber(request.getPhoneNumber());
		if(eContacts.isEmpty() && pContacts.isEmpty()) {
			Contact finalContact = new Contact(0,
					request.getPhoneNumber(),
					request.getEmail(),
					null,
					"primary",
					LocalDateTime.now(),
					LocalDateTime.now(),
					null);
			contactRepository.save(finalContact);
			return new ResponseEntity<>("New Primary User Created Successfully", HttpStatus.CREATED);
		}
		long eContactCount = eContacts.stream().count();
		long pContactCount = pContacts.stream().count();

		if(eContactCount != 0 && pContactCount != 0){
			primaryToSecondary(eContacts,pContacts);
			return new ResponseEntity<>("Contact Modified",HttpStatus.CREATED);
		}

		List<Contact> existingContacts = eContactCount > pContactCount ? eContacts : pContacts ;

		Optional<Integer> linkedId = existingContacts.stream().filter(c->c.getLinkPrecedence().equals("primary"))
				.map(c-> c.getId()).findFirst();

		Contact finalContact = new Contact(0,
				request.getPhoneNumber(),
				request.getEmail(),
				linkedId.get(),
				"secondary",
				LocalDateTime.now(),
				LocalDateTime.now(),
				null);
		contactRepository.save(finalContact);
		return new ResponseEntity<>("New Secondary User Created Successfully",HttpStatus.CREATED);
	}
	

	public void primaryToSecondary(List<Contact> eContacts, List<Contact> pContacts){
		LocalDateTime eCreatedAt = eContacts.stream()
				.filter(r->r.getLinkPrecedence().equals("primary"))
				.map(r->r.getCreatedAt()).findFirst().get();
		LocalDateTime pCreatedAt = pContacts.stream()
				.filter(r->r.getLinkPrecedence().equals("primary"))
				.map(r->r.getCreatedAt()).findFirst().get();

		if(eCreatedAt.isBefore(pCreatedAt)){

			int id =eContacts.stream()
					.filter(r->r.getLinkPrecedence().equals("primary"))
							.map(r->r.getId()).findFirst().get();
			pContacts.stream()
					.filter(r->r.getLinkPrecedence().equals("primary"))
					.map(contact -> modify(contact,id)).findFirst();
		}
		else {
			int id =pContacts.stream()
					.filter(r->r.getLinkPrecedence().equals("primary"))
					.map(r->r.getId()).findFirst().get();
			eContacts.stream()
					.filter(r->r.getLinkPrecedence().equals("primary"))
					.map(contact -> modify(contact,id)).findFirst();
		}
	}
	private Object modify(Contact contact, int id) {
		contact.setUpdatedAt(LocalDateTime.now());
		contact.setLinkPrecedence("secondary");
		contact.setLinkedId(id);
		contactRepository.save(contact);
		return contact;
	}

	public UserContactSummary createSummaryOnlyWithEmail(String email){

		List<Contact> eContacts = contactRepository.findByEmail(email);
		Contact primaryContact =null;
		Boolean isPrimary=false;
		int primaryId;

		for(Contact c: eContacts){
			if(c.getLinkPrecedence().equals("primary")){
				primaryContact = c;
				isPrimary = true;
			}
		}

		if(isPrimary) {
			primaryId = eContacts.stream()
					.map(r -> r.getId()).findFirst().get();
		}
		else{
			primaryId = eContacts.stream()
					.map(r->r.getLinkedId()).findFirst().get();
		}

		List<Contact> secondaryContacts = contactRepository.findByLinkedId(primaryId);
		primaryContact = (primaryContact == null)?contactRepository.findById(primaryId).get():primaryContact;
		//primaryContact = contactRepository.findById(primaryId).get();

		List<String> phoneNumberList = new ArrayList<>();
		phoneNumberList.add(primaryContact.getPhoneNumber());
		for (Contact c:secondaryContacts) {
			phoneNumberList.add(c.getPhoneNumber());
		}
		phoneNumberList = phoneNumberList.stream().distinct().toList();

		List<String> emailList =new ArrayList<>();
		emailList.add(primaryContact.getEmail());
		for (Contact c:secondaryContacts) {
			emailList.add(c.getEmail());
		}
		emailList = emailList.stream().distinct().toList();

		List<Integer> secondaryContactsId = new ArrayList<>();
		for (Contact c:secondaryContacts) {
			secondaryContactsId.add(c.getId());
		}
		return new UserContactSummary(primaryId,emailList,phoneNumberList,secondaryContactsId);
	}

	public UserContactSummary createSummaryOnlyWithNumber(String phoneNumber){

		List<Contact> eContacts = contactRepository.findByPhoneNumber(phoneNumber);
		Contact primaryContact =null;
		Boolean isPrimary=false;
		int primaryId;

		for(Contact c: eContacts){
			if(c.getLinkPrecedence().equals("primary")){
				primaryContact = c;
				isPrimary = true;
			}
		}

		if(isPrimary) {
			primaryId = eContacts.stream()
					.map(r -> r.getId()).findFirst().get();
		}
		else{
			primaryId = eContacts.stream()
					.map(r->r.getLinkedId()).findFirst().get();
		}

		List<Contact> secondaryContacts = contactRepository.findByLinkedId(primaryId);
		primaryContact = (primaryContact == null)?contactRepository.findById(primaryId).get():primaryContact;
		//primaryContact = contactRepository.findById(primaryId).get();

		List<String> phoneNumberList = new ArrayList<>();
		phoneNumberList.add(primaryContact.getPhoneNumber());
		for (Contact c:secondaryContacts) {
			phoneNumberList.add(c.getPhoneNumber());
		}
		phoneNumberList = phoneNumberList.stream().distinct().toList();

		List<String> emailList =new ArrayList<>();
		emailList.add(primaryContact.getEmail());
		for (Contact c:secondaryContacts) {
			emailList.add(c.getEmail());
		}
		emailList = emailList.stream().distinct().toList();

		List<Integer> secondaryContactsId = new ArrayList<>();
		for (Contact c:secondaryContacts) {
			secondaryContactsId.add(c.getId());
		}

		return new UserContactSummary(primaryId,emailList,phoneNumberList,secondaryContactsId);
	}

	public Boolean isContactExist(IdentifyRequest request){
		if(contactRepository.findByEmail(request.getEmail()).isEmpty() && contactRepository.findByPhoneNumber(request.getPhoneNumber()).isEmpty()){
			return true;
		}
		return false;
	}


}
