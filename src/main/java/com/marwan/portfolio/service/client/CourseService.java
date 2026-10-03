package com.marwan.portfolio.service.client;

import com.marwan.portfolio.entity.Course;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

	private final CourseRepository courseRepository;

	@Cacheable("courses")
	public List<Course> getActiveCourses() {
		return courseRepository.findByIsActiveTrue();
	}

	public Course getActiveCourse(Long id) {
		Optional<Course> course = courseRepository.findByIdAndIsActiveTrue(id);

		if (course.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "Course not found");
		}

		return course.get();
	}

}
