package com.marwan.portfolio.service.client;

import com.marwan.portfolio.dto.ContactMessageRequest;
import com.marwan.portfolio.entity.ContactMessage;
import com.marwan.portfolio.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {

	private final ContactMessageRepository contactMessageRepository;

	private final JavaMailSender mailSender;

	// the gmail account that sends and recieves the email (marwan10huss@gmail.com)
	@Value("${spring.mail.username}")
	private String mailUsername;

	// build the email record in database
	public void sendEmail(ContactMessageRequest request) {
		ContactMessage contactMessage = new ContactMessage();
		contactMessage.setName(request.name().trim());
		contactMessage.setEmail(request.email().trim());
		contactMessage.setSubject(request.subject());
		contactMessage.setMessage(request.message().trim());
		contactMessage.setIsRead(false);
		contactMessageRepository.save(contactMessage);

		log.info("Sending to {} message {}", mailUsername, contactMessage.getId());
	}

	// send the actual email
	private void sendEmail(ContactMessage contactMessage) {

		String subject = contactMessage.getSubject();

		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom(mailUsername);
		email.setTo(mailUsername);

		// replying in gmail goes straight to the visitor
		email.setReplyTo(contactMessage.getEmail());
		email.setSubject("Portfolio contact: " + subject);
		email.setText("From: " + contactMessage.getName() + " <" + contactMessage.getEmail() + ">\n\n" + contactMessage.getMessage());

		// the message is already saved, so a failed email should not fail the request
		try {
			mailSender.send(email);
		} catch (MailException e) {
			log.warn("Could not send email for contact message {}: {}", contactMessage.getId(), e.getMessage(), e);
		}
	}

}
