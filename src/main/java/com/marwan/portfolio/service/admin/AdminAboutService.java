package com.marwan.portfolio.service.admin;

import com.marwan.portfolio.dto.AboutRequest;
import com.marwan.portfolio.dto.ReorderRequest;
import com.marwan.portfolio.entity.About;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.AboutRepository;
import com.marwan.portfolio.service.CloudinaryService;
import com.marwan.portfolio.service.ReorderService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminAboutService {

	private final AboutRepository aboutRepository;
	private final CloudinaryService cloudinaryService;
	private final ReorderService reorderService;

	public About getAbout() {
		Optional<About> about = aboutRepository.findFirstByOrderByIdAsc();

		if (about.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "About section is not configured yet");
		}

		return about.get();
	}

	// creates the about record the first time, updates it after that
	@CacheEvict(value = "about", allEntries = true)
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
		about.setImage(cloudinaryService.uploadImageOrKeep(request.imageBase64(), request.image()));
		about.setLocation(request.location());
		about.setEmail(request.email());
		about.setResumeLink(request.resumeLink());
		about.setGithubLink(request.githubLink());
		about.setLinkedinLink(request.linkedinLink());
		about.setProjectDescription(request.projectDescription());
	}

	@CacheEvict(value = "about", allEntries = true)
    public void reorder(List<ReorderRequest> incomingReordering) {
		reorderService.reorder(aboutRepository,incomingReordering);

    }
}
