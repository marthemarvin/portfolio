package com.marwan.portfolio.dto;

// one item of a reorder request, e.g. { "id": 3, "position": 0 }

public record ReorderRequest(
		Long id,
		Integer position) {
}
