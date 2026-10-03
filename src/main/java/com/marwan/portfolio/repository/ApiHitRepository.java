package com.marwan.portfolio.repository;

import com.marwan.portfolio.entity.ApiHit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApiHitRepository extends JpaRepository<ApiHit, Long> {

	// one atomic statement, so two requests at the same time can't overwrite each other's increment
	@Modifying
	@Query(value = """
			INSERT INTO api_hits (endpoint, method, hit_count, created_at, updated_at)
			VALUES (:endpoint, :method, 1, now(), now())
			ON CONFLICT (endpoint, method)
			DO UPDATE SET hit_count = api_hits.hit_count + 1, updated_at = now()
			""", nativeQuery = true)
	void incrementHit(@Param("endpoint") String endpoint, @Param("method") String method);

	List<ApiHit> findAllByOrderByHitCountDesc();

}
