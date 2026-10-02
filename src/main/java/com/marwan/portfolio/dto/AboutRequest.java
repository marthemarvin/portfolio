package com.marwan.portfolio.dto;

public record AboutRequest(
		String fullName,
		String headline,
		String bio,
		String image,
		// base64 data uri, uploaded to cloudinary when sent, otherwise image is kept
		String imageBase64,
		String location,
		String email,
		String resumeLink,
		String githubLink,
		String linkedinLink) {
}
