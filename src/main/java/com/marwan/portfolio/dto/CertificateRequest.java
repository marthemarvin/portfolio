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
		Boolean isActive) {
}
