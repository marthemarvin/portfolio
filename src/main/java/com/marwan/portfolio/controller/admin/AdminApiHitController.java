package com.marwan.portfolio.controller.admin;

import com.marwan.portfolio.entity.ApiHit;
import com.marwan.portfolio.service.admin.AdminApiHitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/stats")
@RequiredArgsConstructor
public class AdminApiHitController {

	private final AdminApiHitService adminApiHitService;

	@GetMapping
	public List<ApiHit> getAllHits() {
		return adminApiHitService.getAllHits();
	}

}
