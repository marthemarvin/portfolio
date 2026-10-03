package com.marwan.portfolio.repository;

import com.marwan.portfolio.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

	List<Course> findByIsActiveTrueOrderByDisplayOrderAsc();

	Optional<Course> findByIdAndIsActiveTrue(Long id);

}
