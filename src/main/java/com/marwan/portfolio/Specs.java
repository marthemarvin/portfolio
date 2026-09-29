package com.marwan.portfolio;

import org.springframework.data.jpa.domain.Specification;

public class Specs {

	// skips the filter when value is null
	public static <T> Specification<T> equal(String field, Object value) {
		if (value == null) {
			return Specification.unrestricted();
		}
		return (root, query, cb) -> cb.equal(root.get(field), value);
	}

	// case-insensitive contains, skips the filter when value is empty
	public static <T> Specification<T> like(String field, String value) {
		if (value == null || value.isBlank()) {
			return Specification.unrestricted();
		}
		return (root, query, cb) -> cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
	}

}
