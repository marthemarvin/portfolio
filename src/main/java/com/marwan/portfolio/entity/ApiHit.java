package com.marwan.portfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

// one row per endpoint + method, hit_count goes up on every request
@Data
@Entity
@Table(name = "api_hits", uniqueConstraints = @UniqueConstraint(columnNames = {"endpoint", "method"}))
public class ApiHit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// route pattern, e.g. /api/courses/{id}
	@Column(name = "endpoint")
	private String endpoint;

	@Column(name = "method")
	private String method;

	@Column(name = "hit_count")
	private Long hitCount;

	@CreationTimestamp
	@Column(name = "created_at")
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Instant updatedAt;

}
