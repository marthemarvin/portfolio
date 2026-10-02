package com.marwan.portfolio.service.admin;

import com.marwan.portfolio.dto.SkillRequest;
import com.marwan.portfolio.entity.Skill;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminSkillService {

	private final SkillRepository skillRepository;

	public Page<Skill> getAllSkills(Pageable pageable) {
		return skillRepository.findAll(pageable);
	}

	public Skill getSkill(Long id) {
		Optional<Skill> skill = skillRepository.findById(id);

		if (skill.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "Skill not found");
		}

		return skill.get();
	}

	public Skill createSkill(SkillRequest request) {
		Skill skill = new Skill();
		mapRequest(skill, request);
		return skillRepository.save(skill);
	}

	public Skill updateSkill(Long id, SkillRequest request) {
		Skill skill = getSkill(id);
		mapRequest(skill, request);
		return skillRepository.save(skill);
	}

	public void deleteSkill(Long id) {
		Skill skill = getSkill(id);
		skillRepository.delete(skill);
	}

	private void mapRequest(Skill skill, SkillRequest request) {
		skill.setName(request.name());
		skill.setCategory(request.category());
		skill.setIcon(request.icon());
		skill.setDisplayOrder(request.displayOrder());
		skill.setIsActive(request.isActive());
	}

}
