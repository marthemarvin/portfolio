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
import java.time.LocalDate;

@Data
@Entity
@Table(name = "certificates")
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	// who issued it, e.g. Oracle, AWS
	@Column(name = "issuer")
	private String issuer;

	@Column(name = "issue_date")
	private LocalDate issueDate;

	// null means it does not expire
	@Column(name = "expiry_date")
	private LocalDate expiryDate;

	@Column(name = "credential_id")
	private String credentialId;

	@Column(name = "credential_url")
	private String credentialUrl;

	@Column(name = "image")
	private String image;

	@Column(name = "is_active")
	private Boolean isActive;

	@CreationTimestamp
	@Column(name = "created_at")
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Instant updatedAt;

}
