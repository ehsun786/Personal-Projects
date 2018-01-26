package eLearning.repository;

import org.springframework.data.repository.CrudRepository;

import eLearning.domain.Lecturer;

public interface LecturerRepository extends CrudRepository<Lecturer, Integer> {
	Lecturer findByusername(String username);
}
