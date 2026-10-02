package com.marwan.portfolio.controller.client;

import com.marwan.portfolio.dto.ContactMessageRequest;
import com.marwan.portfolio.service.client.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

	private final ContactService contactService;

	@PostMapping
	public void buildEmailRecord(@Valid @RequestBody ContactMessageRequest request) {
		contactService.sendEmail(request);
	}

}
