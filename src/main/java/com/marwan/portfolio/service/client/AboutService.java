package com.marwan.portfolio.service.client;

import com.marwan.portfolio.dto.AboutResponse;
import com.marwan.portfolio.entity.About;
import com.marwan.portfolio.entity.AdditionalInfo;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.AboutRepository;
import com.marwan.portfolio.repository.AdditionalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AboutService {

	private final AboutRepository aboutRepository;

	private final AdditionalInfoRepository additionalInfoRepository;

	@Cacheable("about")
	public AboutResponse getAbout() {
		Optional<About> about = aboutRepository.findFirstByOrderByIdAsc();

		if (about.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "About section is not configured yet");
		}

		List<AdditionalInfo> additionalInfo = additionalInfoRepository.findByIsActiveTrueOrderByDisplayOrderAsc();

		return new AboutResponse(about.get(), additionalInfo);
	}

}
