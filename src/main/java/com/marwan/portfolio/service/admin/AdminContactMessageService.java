package com.marwan.portfolio.service.admin;

import com.marwan.portfolio.entity.ContactMessage;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminContactMessageService {

	private final ContactMessageRepository contactMessageRepository;

	public Page<ContactMessage> getAllMessages(Pageable pageable) {
		return contactMessageRepository.findAll(pageable);
	}

	public ContactMessage getMessage(Long id) {
		Optional<ContactMessage> contactMessage = contactMessageRepository.findById(id);

		if (contactMessage.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "Message not found");
		}

		return contactMessage.get();
	}

	public ContactMessage markAsRead(Long id) {
		ContactMessage contactMessage = getMessage(id);
		contactMessage.setIsRead(true);
		return contactMessageRepository.save(contactMessage);
	}

	public void deleteMessage(Long id) {
		ContactMessage contactMessage = getMessage(id);
		contactMessageRepository.delete(contactMessage);
	}

	public long getUnreadMessages() {
		return contactMessageRepository.countByIsReadFalse();
	}

}
