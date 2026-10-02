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
		String companyLink,
		Boolean isActive) {
}
