package com.marwan.portfolio.service.client;

import com.marwan.portfolio.entity.Experience;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExperienceService {

	private final ExperienceRepository experienceRepository;

	@Cacheable("experiences")
	public Page<List<Experience>> getActiveExperiences(int page, int size) {
		Pageable pageable = PageRequest.of(page,size);
		return experienceRepository.findByIsActiveTrueOrderByDisplayOrderAsc(pageable);
	}

	public Experience getActiveExperience(Long id) {
		Optional<Experience> experience = experienceRepository.findByIdAndIsActiveTrue(id);

		if (experience.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "Experience not found");
		}

		return experience.get();
	}

}
