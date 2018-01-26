package eLearning.repository;

import org.springframework.data.repository.CrudRepository;

import eLearning.domain.Reviews;

public interface ReviewsRepository extends CrudRepository<Reviews, Integer> {
	public Reviews findById(int id);
}

