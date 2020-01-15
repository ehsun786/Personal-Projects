package com.ehsun.eventsavy.repos;

import org.springframework.data.repository.CrudRepository;

import com.ehsun.eventsavy.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, String> {

	public Category findByCategory(String category);

}
