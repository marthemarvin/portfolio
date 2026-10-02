package com.marwan.portfolio.dto;

import java.time.LocalDate;

public record ExperienceRequest(
		String companyName,
		String position,
		String location,
		LocalDate startDate,
		LocalDate endDate,
		String description,
		String companyLogo,
		// base64 data uri, uploaded to cloudinary when sent, otherwise companyLogo is kept
		String companyLogoBase64,
		String companyLink,
		Boolean isActive) {
}
