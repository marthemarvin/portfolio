package com.marwan.portfolio.controller.client;

import com.marwan.portfolio.entity.Course;
import com.marwan.portfolio.service.client.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

	private final CourseService courseService;

	@GetMapping
	public List<Course> getActiveCourses() {
		return courseService.getActiveCourses();
	}

	@GetMapping("/{id}")
	public Course getActiveCourse(@PathVariable Long id) {
		return courseService.getActiveCourse(id);
	}

}
