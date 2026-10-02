package com.marwan.portfolio.controller.client;

import com.marwan.portfolio.dto.ContactMessageRequest;
import com.marwan.portfolio.service.client.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

	private final ContactService contactService;

	@PostMapping
	public void sendMessage(@Valid @RequestBody ContactMessageRequest request) {
		contactService.sendMessage(request);
	}

}
