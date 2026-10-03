package com.marwan.portfolio.controller.admin;

import com.marwan.portfolio.dto.CertificateRequest;
import com.marwan.portfolio.dto.ReorderRequest;
import com.marwan.portfolio.entity.Certificate;
import com.marwan.portfolio.service.admin.AdminCertificateService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/certificates")
@RequiredArgsConstructor
public class AdminCertificateController {

	private final AdminCertificateService adminCertificateService;

	@GetMapping
	public Page<Certificate> getAllCertificates(Pageable pageable) {
		return adminCertificateService.getAllCertificates(pageable);
	}

	@GetMapping("/{id}")
	public Certificate getCertificate(@PathVariable Long id) {
		return adminCertificateService.getCertificate(id);
	}

	@PostMapping
	public Certificate createCertificate(@RequestBody CertificateRequest request) {
		return adminCertificateService.createCertificate(request);
	}

	@PutMapping("/{id}")
	public Certificate updateCertificate(@PathVariable Long id, @RequestBody CertificateRequest request) {
		return adminCertificateService.updateCertificate(id, request);
	}

	// body: [{ "id": 3, "position": 0 }, { "id": 1, "position": 1 }]
	@PutMapping("/reorder")
	public void reorderCertificates(@RequestBody List<ReorderRequest> items) {
		adminCertificateService.reorderCertificates(items);
	}

	@DeleteMapping("/{id}")
	public void deleteCertificate(@PathVariable Long id) {
		adminCertificateService.deleteCertificate(id);
	}

}
