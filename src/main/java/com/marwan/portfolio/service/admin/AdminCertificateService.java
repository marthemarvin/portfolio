package com.marwan.portfolio.service.admin;

import com.marwan.portfolio.dto.CertificateRequest;
import com.marwan.portfolio.entity.Certificate;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.CertificateRepository;
import com.marwan.portfolio.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminCertificateService {

	private final CertificateRepository certificateRepository;

	private final CloudinaryService cloudinaryService;

	public Page<Certificate> getAllCertificates(Pageable pageable) {
		return certificateRepository.findAll(pageable);
	}

	public Certificate getCertificate(Long id) {
		Optional<Certificate> certificate = certificateRepository.findById(id);

		if (certificate.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "Certificate not found");
		}

		return certificate.get();
	}

	@CacheEvict(value = "certificates", allEntries = true)
	public Certificate createCertificate(CertificateRequest request) {
		Certificate certificate = new Certificate();
		mapRequest(certificate, request);
		return certificateRepository.save(certificate);
	}

	@CacheEvict(value = "certificates", allEntries = true)
	public Certificate updateCertificate(Long id, CertificateRequest request) {
		Certificate certificate = getCertificate(id);
		mapRequest(certificate, request);
		return certificateRepository.save(certificate);
	}

	@CacheEvict(value = "certificates", allEntries = true)
	public void deleteCertificate(Long id) {
		Certificate certificate = getCertificate(id);
		certificateRepository.delete(certificate);
	}

	private void mapRequest(Certificate certificate, CertificateRequest request) {
		certificate.setName(request.name());
		certificate.setIssuer(request.issuer());
		certificate.setIssueDate(request.issueDate());
		certificate.setExpiryDate(request.expiryDate());
		certificate.setCredentialId(request.credentialId());
		certificate.setCredentialUrl(request.credentialUrl());
		certificate.setImage(cloudinaryService.uploadImageOrKeep(request.imageBase64(), request.image()));
		certificate.setIsActive(request.isActive());
	}

}
