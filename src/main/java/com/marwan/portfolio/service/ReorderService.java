package com.marwan.portfolio.service;

import com.marwan.portfolio.dto.ReorderRequest;
import com.marwan.portfolio.entity.Positionable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// shared by every admin service that supports reordering
@Service
public class ReorderService {

	// <T extends Positionable> means T can be any entity, as long as it implements Positionable,
	// so we can call getId() and setDisplayOrder() without knowing if it's a Course, Skill, ...
	// the caller passes its own repository, e.g. courseRepository makes T = Course
	@Transactional


	public <T extends Positionable> void reorder(JpaRepository<T, Long> repository, List<ReorderRequest> incomingPositionRequests) {

		List<Long> incomingIds = incomingPositionRequests.stream()
				.map((ReorderRequest r)->r.id()).toList();

		List<T> all = repository.findAllById(incomingIds);

		Map<Long, T> map = all.stream()
				.collect(Collectors.toMap(
						(T entity) -> entity.getId(),   // the key: each entity's id
						(T entity) -> entity            // the value: the entity itself
				));

		for(ReorderRequest incomingRequest : incomingPositionRequests ){
			T entity = map.get(incomingRequest.id());
			entity.setDisplayOrder(incomingRequest.position());
		}

		repository.saveAll(map.values());
	}
}


//for (ReorderRequest request : incomingPositionRequests) {
//		T entity = repository.findById(request.id()).get();
//			entity.setDisplayOrder(request.position());

//		}