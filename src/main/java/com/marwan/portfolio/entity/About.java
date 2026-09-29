package com.marwan.portfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Entity
@Table(name = "about")
public class About {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "headline")
	private String headline;

	@Column(name = "bio", columnDefinition = "TEXT")
	private String bio;

	@Column(name = "image")
	private String image;

	@Column(name = "location")
	private String location;

	@Column(name = "email")
	private String email;

	@Column(name = "resume_link")
	private String resumeLink;

	@Column(name = "github_link")
	private String githubLink;

	@Column(name = "linkedin_link")
	private String linkedinLink;

	@CreationTimestamp
	@Column(name = "created_at")
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Instant updatedAt;

}
