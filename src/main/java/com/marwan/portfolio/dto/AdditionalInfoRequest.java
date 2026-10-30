package com.marwan.portfolio.dto;

public record AdditionalInfoRequest(
		String title,
		String description,
		String image,
		// base64 data uri, uploaded to cloudinary when sent, otherwise image is kept
		String imageBase64,
		Boolean isActive) {
}
