package com.marwan.portfolio.controller.client;

import com.marwan.portfolio.entity.Skill;
import com.marwan.portfolio.service.client.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

	private final SkillService skillService;

	@GetMapping
	public List<Skill> getActiveSkills() {
		return skillService.getActiveSkills();
	}

	@GetMapping("/{id}")
	public Skill getActiveSkill(@PathVariable Long id) {
		return skillService.getActiveSkill(id);
	}

}
