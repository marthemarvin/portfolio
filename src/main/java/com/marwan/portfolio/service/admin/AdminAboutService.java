package com.marwan.portfolio.service.admin;

import com.marwan.portfolio.dto.AboutRequest;
import com.marwan.portfolio.entity.About;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.AboutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminAboutService {

	private final AboutRepository aboutRepository;

	public About getAbout() {
		Optional<About> about = aboutRepository.findFirstByOrderByIdAsc();

		if (about.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "About section is not configured yet");
		}

		return about.get();
	}

	// creates the about record the first time, updates it after that
	public About saveAbout(AboutRequest request) {
		Optional<About> existing = aboutRepository.findFirstByOrderByIdAsc();

		About about = new About();
		if (existing.isPresent()) {
			about = existing.get();
		}

		mapRequest(about, request);
		return aboutRepository.save(about);
	}

	private void mapRequest(About about, AboutRequest request) {
		about.setFullName(request.fullName());
		about.setHeadline(request.headline());
		about.setBio(request.bio());
		about.setImage(request.image());
		about.setLocation(request.location());
		about.setEmail(request.email());
		about.setResumeLink(request.resumeLink());
		about.setGithubLink(request.githubLink());
		about.setLinkedinLink(request.linkedinLink());
	}

}
