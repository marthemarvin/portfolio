package com.marwan.portfolio.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

	@Bean
	public Cloudinary cloudinary(@Value("${cloudinary.url}") String cloudinaryUrl) {
		// without a url the app still starts, uploads just fail until CLOUDINARY_URL is set
		if (cloudinaryUrl == null || cloudinaryUrl.isBlank()) {
			return new Cloudinary();
		}
		return new Cloudinary(cloudinaryUrl);
	}

}
