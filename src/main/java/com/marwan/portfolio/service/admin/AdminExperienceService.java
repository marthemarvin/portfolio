package com.marwan.portfolio.service.admin;

import com.marwan.portfolio.dto.ExperienceRequest;
import com.marwan.portfolio.entity.Experience;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminExperienceService {

	private final ExperienceRepository experienceRepository;

	public Page<Experience> getAllExperiences(Pageable pageable) {
		return experienceRepository.findAll(pageable);
	}

	public Experience getExperience(Long id) {
		Optional<Experience> experience = experienceRepository.findById(id);

		if (experience.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "Experience not found");
		}

		return experience.get();
	}

	public Experience createExperience(ExperienceRequest request) {
		Experience experience = new Experience();
		mapRequest(experience, request);
		return experienceRepository.save(experience);
	}

	public Experience updateExperience(Long id, ExperienceRequest request) {
		Experience experience = getExperience(id);
		mapRequest(experience, request);
		return experienceRepository.save(experience);
	}

	public void deleteExperience(Long id) {
		Experience experience = getExperience(id);
		experienceRepository.delete(experience);
	}

	private void mapRequest(Experience experience, ExperienceRequest request) {
		experience.setCompanyName(request.companyName());
		experience.setPosition(request.position());
		experience.setLocation(request.location());
		experience.setStartDate(request.startDate());
		experience.setEndDate(request.endDate());
		experience.setDescription(request.description());
		experience.setCompanyLogo(request.companyLogo());
		experience.setCompanyLink(request.companyLink());
		experience.setIsActive(request.isActive());
	}

}
