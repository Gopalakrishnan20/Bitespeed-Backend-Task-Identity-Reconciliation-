package com.bitspeed.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.bitspeed.model.IdentifyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitspeed.model.Contact;
import com.bitspeed.model.UserSummary;
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
	
	public UserSummary makeSummary(String email, String phoneNumber) {
		List<Contact> eContacts = contactRepository.findByEmail(email);
		List<Contact> pContacts = contactRepository.findByPhoneNumber(phoneNumber);
		
		long eContactCount = eContacts.stream().count();
		long pContactCount = pContacts.stream().count();
		
		 List<Contact> result = eContactCount > pContactCount ? eContacts : pContacts;
		 
		 Stream<Object> primaryContactId = result.stream()
				 .filter(r ->r.getLinkPrecedence().equals("primary"))
				 .map(r -> r.getId());
		 
		 Stream<Object> rEmail =  result.stream().map(r -> r.getEmail());
		 
		 Stream<String> rPhoneNumber = result.stream()
				 .map(r -> r.getPhoneNumber()).distinct();
		 
		 Stream<Object> rSecondaryContactId = result.stream()
				 .filter(r ->r.getLinkPrecedence().equals("secondary"))
				 .map(r -> r.getId());
		 
		 UserSummary summary = new UserSummary(
				 primaryContactId.findFirst().get(),
				 rEmail.toList(),
				 rPhoneNumber.toList(),
				 rSecondaryContactId.findFirst().get()
				 );
		 return summary;
		 
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
			System.out.println(id);
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

}
