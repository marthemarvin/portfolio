package com.marwan.portfolio.service.client;

import com.marwan.portfolio.dto.ContactMessageRequest;
import com.marwan.portfolio.entity.ContactMessage;
import com.marwan.portfolio.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

	private final ContactMessageRepository contactMessageRepository;

	// the request is already validated by the annotations on ContactMessageRequest
	public void sendMessage(ContactMessageRequest request) {
		ContactMessage contactMessage = new ContactMessage();
		contactMessage.setName(request.name().trim());
		contactMessage.setEmail(request.email().trim());
		contactMessage.setSubject(request.subject());
		contactMessage.setMessage(request.message().trim());
		contactMessage.setIsRead(false);
		contactMessageRepository.save(contactMessage);
	}

}
