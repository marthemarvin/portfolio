package com.marwan.portfolio.dto;

public record AboutRequest(
		String fullName,
		String headline,
		String bio,
		String image,
		String location,
		String email,
		String resumeLink,
		String githubLink,
		String linkedinLink) {
}
