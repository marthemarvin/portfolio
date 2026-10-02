package com.marwan.portfolio.repository;

import com.marwan.portfolio.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long>, JpaSpecificationExecutor<Skill> {

	// grouped by category, then in display order
	List<Skill> findByIsActiveTrueOrderByCategoryAscDisplayOrderAsc();

	Optional<Skill> findByIdAndIsActiveTrue(Long id);

}
