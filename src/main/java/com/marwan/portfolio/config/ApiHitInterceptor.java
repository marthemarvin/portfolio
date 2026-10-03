package com.marwan.portfolio.config;

import com.marwan.portfolio.service.ApiHitService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

@Component
@RequiredArgsConstructor
public class ApiHitInterceptor implements HandlerInterceptor {

	private final ApiHitService apiHitService;

	// spring calls this after every request
	// handler is whatever Spring chose to handle the request. For your endpoints, that's a specific method on one of your controllers.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

		// only count requests handled by a controller, unknown urls fall through to the static "/**" handler
		if (!(handler instanceof HandlerMethod)) {
			return;
		}

		// the matched route, e.g. /api/courses/{id}, so every id counts as one endpoint
		String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		apiHitService.recordHit(pattern, request.getMethod());
	}

}
