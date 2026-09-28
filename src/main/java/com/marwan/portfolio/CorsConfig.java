package com.marwan.portfolio;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	// Spring Framework creates and passes the CorsRegistry object.
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("https://marwankw.com", "http://localhost:4200")
				.allowedMethods("GET", "POST", "PUT", "DELETE");
	}

}
