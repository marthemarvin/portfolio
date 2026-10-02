package com.marwan.portfolio.controller;

import com.marwan.portfolio.entity.Experience;
import com.marwan.portfolio.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
@RequiredArgsConstructor
public class ExperienceController {

	private final ExperienceService experienceService;

	@GetMapping
	public Page<List<Experience>> getActiveExperiences(@RequestParam int page, @RequestParam int size) {
		return experienceService.getActiveExperiences(page,size);
	}

	@GetMapping("/{id}")
	public Experience getActiveExperience(@PathVariable Long id) {
		return experienceService.getActiveExperience(id);
	}

}
