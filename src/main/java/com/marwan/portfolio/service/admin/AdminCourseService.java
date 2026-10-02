package com.marwan.portfolio.service.admin;

import com.marwan.portfolio.dto.CourseRequest;
import com.marwan.portfolio.entity.Course;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.CourseRepository;
import com.marwan.portfolio.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminCourseService {

	private final CourseRepository courseRepository;

	private final CloudinaryService cloudinaryService;

	public Page<Course> getAllCourses(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}

	public Course getCourse(Long id) {
		Optional<Course> course = courseRepository.findById(id);

		if (course.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "Course not found");
		}

		return course.get();
	}

	public Course createCourse(CourseRequest request) {
		Course course = new Course();
		mapRequest(course, request);
		return courseRepository.save(course);
	}

	public Course updateCourse(Long id, CourseRequest request) {
		Course course = getCourse(id);
		mapRequest(course, request);
		return courseRepository.save(course);
	}

	public void deleteCourse(Long id) {
		Course course = getCourse(id);
		courseRepository.delete(course);
	}

	private void mapRequest(Course course, CourseRequest request) {
		course.setTitle(request.title());
		course.setDescription(request.description());
		course.setAuthor(request.author());
		course.setLink(request.link());
		course.setImage(cloudinaryService.uploadImageOrKeep(request.imageBase64(), request.image()));
		course.setIsActive(request.isActive());
	}

}
