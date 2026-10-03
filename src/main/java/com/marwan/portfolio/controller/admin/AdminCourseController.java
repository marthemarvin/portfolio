package com.marwan.portfolio.controller.admin;

import com.marwan.portfolio.dto.CourseRequest;
import com.marwan.portfolio.dto.ReorderRequest;
import com.marwan.portfolio.entity.Course;
import com.marwan.portfolio.service.admin.AdminCourseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/courses")
@RequiredArgsConstructor
public class AdminCourseController {

	private final AdminCourseService adminCourseService;

	@GetMapping
	public Page<Course> getAllCourses(Pageable pageable) {
		return adminCourseService.getAllCourses(pageable);
	}

	@GetMapping("/{id}")
	public Course getCourse(@PathVariable Long id) {
		return adminCourseService.getCourse(id);
	}

	@PostMapping
	public Course createCourse(@RequestBody CourseRequest request) {
		return adminCourseService.createCourse(request);
	}

	@PutMapping("/{id}")
	public Course updateCourse(@PathVariable Long id, @RequestBody CourseRequest request) {
		return adminCourseService.updateCourse(id, request);
	}

	@PutMapping("/reorder")
	public void reorderCourses(@RequestBody List<ReorderRequest> items) {
		adminCourseService.reorderCourses(items);
	}

	@DeleteMapping("/{id}")
	public void deleteCourse(@PathVariable Long id) {
		adminCourseService.deleteCourse(id);
	}

}
