package com.marwan.portfolio.dto;

public record SkillRequest(
		String name,
		String category,
		String icon,
		Integer displayOrder,
		Boolean isActive) {
}
