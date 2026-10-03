package com.marwan.portfolio.service.admin;

import com.marwan.portfolio.dto.CourseRequest;
import com.marwan.portfolio.dto.ReorderRequest;
import com.marwan.portfolio.entity.Course;
import com.marwan.portfolio.exception.ApiException;
import com.marwan.portfolio.repository.CourseRepository;
import com.marwan.portfolio.service.CloudinaryService;
import com.marwan.portfolio.service.ReorderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
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

	private final ReorderService reorderService;

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

	@CacheEvict(value = "courses", allEntries = true)
	public Course createCourse(CourseRequest request) {
		Course course = new Course();
		mapRequest(course, request);
		return courseRepository.save(course);
	}

	@CacheEvict(value = "courses", allEntries = true)
	public Course updateCourse(Long id, CourseRequest request) {
		Course course = getCourse(id);
		mapRequest(course, request);
		return courseRepository.save(course);
	}

	@CacheEvict(value = "courses", allEntries = true)
	public void deleteCourse(Long id) {
		Course course = getCourse(id);
		courseRepository.delete(course);
	}

	@CacheEvict(value = "courses", allEntries = true)
	public void reorderCourses(List<ReorderRequest> items) {
		reorderService.reorder(courseRepository, items);
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
