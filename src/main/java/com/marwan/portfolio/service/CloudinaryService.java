package com.marwan.portfolio.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.marwan.portfolio.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

// shared by any service that needs to store an image
@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryService {

	private static final String CLOUDINARY_FOLDER = "portfolio";

	private final Cloudinary cloudinary;

	// uploads the base64 image when one is sent, otherwise keeps the current url
	public String uploadImageOrKeep(String base64Image, String currentUrl) {
		if (base64Image == null || base64Image.isBlank()) {
			return currentUrl;
		}
		return uploadImage(base64Image);
	}

	// takes a base64 data uri ("data:image/png;base64,...") and returns its url
	public String uploadImage(String base64Image) {
		if (base64Image == null || base64Image.isBlank()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Image is required");
		}

		try {
			Map<?, ?> result = cloudinary.uploader().upload(base64Image.trim(), ObjectUtils.asMap(
					"folder", CLOUDINARY_FOLDER,
					"resource_type", "image"));
			return String.valueOf(result.get("secure_url"));
		}
		catch (Exception e) {
			log.warn("Cloudinary upload failed: {}", e.getMessage(), e);
			throw new ApiException(HttpStatus.BAD_GATEWAY, "Image upload failed");
		}
	}

}
