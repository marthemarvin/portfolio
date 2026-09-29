package com.marwan.portfolio.dto;

public record CourseRequest(
		String title,
		String description,
		String author,
		String link,
		String image,
		Boolean isActive) {
}
