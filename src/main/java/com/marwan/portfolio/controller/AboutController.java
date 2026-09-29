package com.marwan.portfolio.controller;

import com.marwan.portfolio.entity.About;
import com.marwan.portfolio.service.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/about")
@RequiredArgsConstructor
public class AboutController {

	private final AboutService aboutService;

	@GetMapping
	public About getAbout() {
		return aboutService.getAbout();
	}

}
