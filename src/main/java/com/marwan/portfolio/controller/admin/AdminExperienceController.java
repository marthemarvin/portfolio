package com.marwan.portfolio.controller.admin;

import com.marwan.portfolio.dto.ExperienceRequest;
import com.marwan.portfolio.entity.Experience;
import com.marwan.portfolio.service.admin.AdminExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/experiences")
@RequiredArgsConstructor
public class AdminExperienceController {

	private final AdminExperienceService adminExperienceService;

	@GetMapping
	public Page<Experience> getAllExperiences(Pageable pageable) {
		return adminExperienceService.getAllExperiences(pageable);
	}

	@GetMapping("/{id}")
	public Experience getExperience(@PathVariable Long id) {
		return adminExperienceService.getExperience(id);
	}

	@PostMapping
	public Experience createExperience(@RequestBody ExperienceRequest request) {
		return adminExperienceService.createExperience(request);
	}

	@PutMapping("/{id}")
	public Experience updateExperience(@PathVariable Long id, @RequestBody ExperienceRequest request) {
		return adminExperienceService.updateExperience(id, request);
	}

	@DeleteMapping("/{id}")
	public void deleteExperience(@PathVariable Long id) {
		adminExperienceService.deleteExperience(id);
	}

}
