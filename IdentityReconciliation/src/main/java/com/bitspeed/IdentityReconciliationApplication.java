package com.bitspeed;

import com.bitspeed.model.Contact;
import com.bitspeed.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class IdentityReconciliationApplication implements CommandLineRunner {

	@Autowired
	ContactRepository contactRepository;

	public static void main(String[] args) {
		SpringApplication.run(IdentityReconciliationApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		contactRepository.save(new Contact(0,
				"123456",
				"lorraine@hillvalley.edu",
				null,
				"primary",
				LocalDateTime.now().plusDays(4),
				LocalDateTime.now(),
				null));

		contactRepository.save(new Contact(0,
				"123456",
				"mcfly@hillvalley.edu",
				1,
				"secondary",
				LocalDateTime.now(),
				LocalDateTime.now(),
				null));

		contactRepository.save(new Contact(0,
				"919191",
				"george@hillvalley.edu",
				null,
				"primary",
				LocalDateTime.now(),
				LocalDateTime.now(),
				null));

		contactRepository.save(new Contact(0,
				"717171",
				"biffsucks@hillvalley.edu",
				null,
				"primary",
				LocalDateTime.now(),
				LocalDateTime.now(),
				null));
	}
}
