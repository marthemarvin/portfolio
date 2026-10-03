package com.marwan.portfolio.service;

import com.marwan.portfolio.repository.ApiHitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApiHitService {

	private final ApiHitRepository apiHitRepository;

	@Transactional
	public void recordHit(String endpoint, String method) {
		apiHitRepository.incrementHit(endpoint, method);
	}

}
