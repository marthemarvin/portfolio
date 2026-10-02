package com.marwan.portfolio.service.client;

import com.marwan.portfolio.entity.Certificate;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificateService {

	private final CertificateRepository certificateRepository;

	public Page<Certificate> getActiveCertificates(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return certificateRepository.findByIsActiveTrueOrderByIssueDateDesc(pageable);
	}

	public Certificate getActiveCertificate(Long id) {
		Optional<Certificate> certificate = certificateRepository.findByIdAndIsActiveTrue(id);

		if (certificate.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "Certificate not found");
		}

		return certificate.get();
	}

}
