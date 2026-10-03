package com.marwan.portfolio.controller.admin;

import com.marwan.portfolio.dto.ReorderRequest;
import com.marwan.portfolio.dto.SkillRequest;
import com.marwan.portfolio.entity.Skill;
import com.marwan.portfolio.service.admin.AdminSkillService;
import java.util.List;
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
@RequestMapping("/api/admin/skills")
@RequiredArgsConstructor
public class AdminSkillController {

	private final AdminSkillService adminSkillService;

	@GetMapping
	public Page<Skill> getAllSkills(Pageable pageable) {
		return adminSkillService.getAllSkills(pageable);
	}

	@GetMapping("/{id}")
	public Skill getSkill(@PathVariable Long id) {
		return adminSkillService.getSkill(id);
	}

	@PostMapping
	public Skill createSkill(@RequestBody SkillRequest request) {
		return adminSkillService.createSkill(request);
	}

	@PutMapping("/{id}")
	public Skill updateSkill(@PathVariable Long id, @RequestBody SkillRequest request) {
		return adminSkillService.updateSkill(id, request);
	}

	@PutMapping("/reorder")
	public void reorderSkills(@RequestBody List<ReorderRequest> items) {
		adminSkillService.reorderSkills(items);
	}

	@DeleteMapping("/{id}")
	public void deleteSkill(@PathVariable Long id) {
		adminSkillService.deleteSkill(id);
	}

}
