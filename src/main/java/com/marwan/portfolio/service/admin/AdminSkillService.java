package com.marwan.portfolio.service.admin;

import com.marwan.portfolio.dto.ReorderRequest;
import com.marwan.portfolio.dto.SkillRequest;
import com.marwan.portfolio.entity.Skill;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.SkillRepository;
import com.marwan.portfolio.service.CloudinaryService;
import com.marwan.portfolio.service.ReorderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminSkillService {

	private final SkillRepository skillRepository;

	private final CloudinaryService cloudinaryService;

	private final ReorderService reorderService;

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

	@CacheEvict(value = "skills", allEntries = true)
	public Skill createSkill(SkillRequest request) {
		Skill skill = new Skill();
		mapRequest(skill, request);
		return skillRepository.save(skill);
	}

	@CacheEvict(value = "skills", allEntries = true)
	public Skill updateSkill(Long id, SkillRequest request) {
		Skill skill = getSkill(id);
		mapRequest(skill, request);
		return skillRepository.save(skill);
	}

	@CacheEvict(value = "skills", allEntries = true)
	public void deleteSkill(Long id) {
		Skill skill = getSkill(id);
		skillRepository.delete(skill);
	}

	@CacheEvict(value = "skills", allEntries = true)
	public void reorderSkills(List<ReorderRequest> items) {
		reorderService.reorder(skillRepository, items);
	}

	private void mapRequest(Skill skill, SkillRequest request) {
		skill.setName(request.name());
		skill.setCategory(request.category());
		skill.setIcon(cloudinaryService.uploadImageOrKeep(request.iconBase64(), request.icon()));
		skill.setDisplayOrder(request.displayOrder());
		skill.setIsActive(request.isActive());
	}

}
