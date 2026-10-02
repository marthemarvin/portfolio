package com.marwan.portfolio.dto;

public record SkillRequest(
		String name,
		String category,
		String icon,
		// base64 data uri, uploaded to cloudinary when sent, otherwise icon is kept
		String iconBase64,
		Integer displayOrder,
		Boolean isActive) {
}
