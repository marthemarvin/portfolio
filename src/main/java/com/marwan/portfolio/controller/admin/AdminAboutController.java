package com.marwan.portfolio.controller.admin;

import com.marwan.portfolio.dto.AboutRequest;
import com.marwan.portfolio.dto.ReorderRequest;
import com.marwan.portfolio.entity.About;
import com.marwan.portfolio.service.admin.AdminAboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/about")
@RequiredArgsConstructor
public class AdminAboutController {

	private final AdminAboutService adminAboutService;

	@GetMapping
	public About getAbout() {
		return adminAboutService.getAbout();
	}

	@PutMapping
	public About saveAbout(@RequestBody AboutRequest request) {
		return adminAboutService.saveAbout(request);
	}

	@PutMapping("/reorder")
	public void reorder(@RequestBody List<ReorderRequest> incomingReordering){
		 adminAboutService.reorder(incomingReordering);
	}

}
