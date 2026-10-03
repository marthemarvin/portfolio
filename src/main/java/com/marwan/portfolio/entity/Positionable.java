package com.marwan.portfolio.entity;

// a promise: "I have an id and you can set my display order"
// any entity that implements this can be reordered by ReorderService
// lombok's @Data already generates both methods, so an entity only needs an id and a displayOrder field
// (named displayOrder, not position, because Experience already uses position for the job title)
public interface Positionable {

	Long getId();

	void setDisplayOrder(Integer displayOrder);

}
