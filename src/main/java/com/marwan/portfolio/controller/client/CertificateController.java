package com.marwan.portfolio.controller.client;

import com.marwan.portfolio.entity.Certificate;
import com.marwan.portfolio.service.client.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

	private final CertificateService certificateService;

	@GetMapping
	public Page<Certificate> getActiveCertificates(@RequestParam int page, @RequestParam int size) {
		return certificateService.getActiveCertificates(page, size);
	}

	@GetMapping("/{id}")
	public Certificate getActiveCertificate(@PathVariable Long id) {
		return certificateService.getActiveCertificate(id);
	}

}
