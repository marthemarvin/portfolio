package com.marwan.portfolio.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// spring takes this bean and does the work not our service
@Configuration
@RequiredArgsConstructor
public class ApiHitConfig implements WebMvcConfigurer {

	private final ApiHitInterceptor apiHitInterceptor;

	// count public api calls only, admin calls are just me testing
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiHitInterceptor)
				.addPathPatterns("/api/**") // only add api hits for non admin (client only)
				.excludePathPatterns("/api/admin/**"); // excludes admin api
	}

}
