package com.marwan.portfolio.dto;

import java.time.LocalDate;

public record CertificateRequest(
		String name,
		String issuer,
		LocalDate issueDate,
		LocalDate expiryDate,
		String credentialId,
		String credentialUrl,
		String image,
		// base64 data uri, uploaded to cloudinary when sent, otherwise image is kept
		String imageBase64,
		Boolean isActive) {
}
