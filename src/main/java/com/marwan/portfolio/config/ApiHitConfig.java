package com.marwan.portfolio.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ApiHitConfig implements WebMvcConfigurer {

	private final ApiHitInterceptor apiHitInterceptor;

	// count public api calls only, admin calls are just me testing
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiHitInterceptor)
				.addPathPatterns("/api/**")
				.excludePathPatterns("/api/admin/**");
	}

}
