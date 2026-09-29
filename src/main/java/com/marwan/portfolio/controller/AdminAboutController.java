package com.marwan.portfolio.controller;

import com.marwan.portfolio.dto.AboutRequest;
import com.marwan.portfolio.entity.About;
import com.marwan.portfolio.service.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/about")
@RequiredArgsConstructor
public class AdminAboutController {

	private final AboutService aboutService;

	@GetMapping
	public About getAbout() {
		return aboutService.getAbout();
	}

	@PutMapping
	public About saveAbout(@RequestBody AboutRequest request) {
		return aboutService.saveAbout(request);
	}

}
