package com.marwan.portfolio.service;

import com.marwan.portfolio.dto.CourseRequest;
import com.marwan.portfolio.entity.Course;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

	private final CourseRepository courseRepository;

	public List<Course> getActiveCourses() {
		return courseRepository.findByIsActiveTrue();
	}

	public Page<Course> getAllCourses(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}

	public Course getCourse(Long id) {
		return courseRepository.findById(id)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Course not found"));
	}

	public Course createCourse(CourseRequest request) {
		Course course = new Course();
		applyRequest(course, request);
		return courseRepository.save(course);
	}

	public Course updateCourse(Long id, CourseRequest request) {
		Course course = getCourse(id);
		applyRequest(course, request);
		return courseRepository.save(course);
	}

	public void deleteCourse(Long id) {
		Course course = getCourse(id);
		courseRepository.delete(course);
	}

	private void applyRequest(Course course, CourseRequest request) {
		course.setTitle(request.title());
		course.setDescription(request.description());
		course.setAuthor(request.author());
		course.setLink(request.link());
		course.setImage(request.image());
		course.setIsActive(request.isActive());
	}

}
