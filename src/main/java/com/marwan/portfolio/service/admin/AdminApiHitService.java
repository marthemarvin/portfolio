package com.marwan.portfolio.service.admin;

import com.marwan.portfolio.entity.ApiHit;
import com.marwan.portfolio.repository.ApiHitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminApiHitService {

	private final ApiHitRepository apiHitRepository;

	// most hit endpoint first
	public List<ApiHit> getAllHits() {
		return apiHitRepository.findAllByOrderByHitCountDesc();
	}

}
