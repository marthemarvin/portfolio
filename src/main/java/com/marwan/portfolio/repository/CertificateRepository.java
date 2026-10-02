package com.marwan.portfolio.repository;

import com.marwan.portfolio.entity.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Long>, JpaSpecificationExecutor<Certificate> {

	// newest certificate first
	Page<Certificate> findByIsActiveTrueOrderByIssueDateDesc(Pageable pageable);

	Optional<Certificate> findByIdAndIsActiveTrue(Long id);

}
