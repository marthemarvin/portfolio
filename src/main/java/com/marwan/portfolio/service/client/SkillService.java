package com.marwan.portfolio.service.client;

import com.marwan.portfolio.entity.Skill;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService {

	private final SkillRepository skillRepository;

	public List<Skill> getActiveSkills() {
		return skillRepository.findByIsActiveTrueOrderByCategoryAscDisplayOrderAsc();
	}

	public Skill getActiveSkill(Long id) {
		Optional<Skill> skill = skillRepository.findByIdAndIsActiveTrue(id);

		if (skill.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "Skill not found");
		}

		return skill.get();
	}

}
