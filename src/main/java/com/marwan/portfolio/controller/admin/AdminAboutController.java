package com.marwan.portfolio.controller.admin;

import com.marwan.portfolio.dto.AboutRequest;
import com.marwan.portfolio.dto.AdditionalInfoRequest;
import com.marwan.portfolio.dto.ReorderRequest;
import com.marwan.portfolio.entity.About;
import com.marwan.portfolio.entity.AdditionalInfo;
import com.marwan.portfolio.service.admin.AdminAboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/additional-info")
	public List<AdditionalInfo> getAllAdditionalInfo() {
		return adminAboutService.getAllAdditionalInfo();
	}

	@GetMapping("/additional-info/{id}")
	public AdditionalInfo getAdditionalInfo(@PathVariable Long id) {
		return adminAboutService.getAdditionalInfo(id);
	}

	@PostMapping("/additional-info")
	public AdditionalInfo createAdditionalInfo(@RequestBody AdditionalInfoRequest request) {
		return adminAboutService.createAdditionalInfo(request);
	}

	@PutMapping("/additional-info/{id}")
	public AdditionalInfo updateAdditionalInfo(@PathVariable Long id, @RequestBody AdditionalInfoRequest request) {
		return adminAboutService.updateAdditionalInfo(id, request);
	}

	@PutMapping("/additional-info/reorder")
	public void reorderAdditionalInfo(@RequestBody List<ReorderRequest> items) {
		adminAboutService.reorderAdditionalInfo(items);
	}

	@DeleteMapping("/additional-info/{id}")
	public void deleteAdditionalInfo(@PathVariable Long id) {
		adminAboutService.deleteAdditionalInfo(id);
	}

}
