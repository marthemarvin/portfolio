package com.marwan.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactMessageRequest(
		@NotBlank(message = "Name is required")
		@Size(max = 255, message = "Name must be at most 255 characters")
		String name,

		@NotBlank(message = "Email is required")
		@Email(message = "Invalid email address")
		@Size(max = 255, message = "Email must be at most 255 characters")
		String email,

		@Size(max = 255, message = "Subject must be at most 255 characters")
		String subject,

		@NotBlank(message = "Message is required")
		@Size(max = 5000, message = "Message must be at most 5000 characters")
		String message) {
}
