package com.marwan.portfolio.service.client;

import com.marwan.portfolio.entity.About;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.AboutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AboutService {

	private final AboutRepository aboutRepository;

	@Cacheable("about")
	public About getAbout() {
		Optional<About> about = aboutRepository.findFirstByOrderByIdAsc();

		if (about.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "About section is not configured yet");
		}

		return about.get();
	}

}
