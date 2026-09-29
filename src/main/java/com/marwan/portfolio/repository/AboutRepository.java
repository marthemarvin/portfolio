package com.marwan.portfolio.repository;

import com.marwan.portfolio.entity.About;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AboutRepository extends JpaRepository<About, Long> {

	// there is only ever one about record
	Optional<About> findFirstByOrderByIdAsc();

}
