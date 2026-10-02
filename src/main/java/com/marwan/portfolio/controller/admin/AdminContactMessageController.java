package com.marwan.portfolio.controller.admin;

import com.marwan.portfolio.entity.ContactMessage;
import com.marwan.portfolio.service.admin.AdminContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/contact-messages")
@RequiredArgsConstructor
public class AdminContactMessageController {

	private final AdminContactMessageService adminContactMessageService;

	@GetMapping
	public Page<ContactMessage> getAllMessages(Pageable pageable) {
		return adminContactMessageService.getAllMessages(pageable);
	}

	@GetMapping("/{id}")
	public ContactMessage getMessage(@PathVariable Long id) {
		return adminContactMessageService.getMessage(id);
	}

	@PutMapping("/{id}/read")
	public ContactMessage markAsRead(@PathVariable Long id) {
		return adminContactMessageService.markAsRead(id);
	}

	@DeleteMapping("/{id}")
	public void deleteMessage(@PathVariable Long id) {
		adminContactMessageService.deleteMessage(id);
	}

	@GetMapping
	public long getUnreadMessages(){
		return adminContactMessageService.getUnreadMessages();
	}
}
