package com.marwan.portfolio.dto;

public record CourseRequest(
		String title,
		String description,
		String author,
		String link,
		String image,
		// base64 data uri, uploaded to cloudinary when sent, otherwise image is kept
		String imageBase64,
		Boolean isActive) {
}
