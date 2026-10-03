package com.marwan.portfolio.repository;

import com.marwan.portfolio.entity.Experience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experience, Long>, JpaSpecificationExecutor<Experience> {

	// in the order set by the reorder endpoint
	Page<List<Experience>> findByIsActiveTrueOrderByDisplayOrderAsc(Pageable pageable);

	Optional<Experience> findByIdAndIsActiveTrue(Long id);

}
