package eLearning.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eLearning.domain.Learner;

public interface LearnersRepository extends CrudRepository<Learner, Integer> {
	
	//@Query(value = "SELECT * FROM learners", nativeQuery = true)
	//public ArrayList<Learner> findAll();
	
	Learner findByusername(String username);
}
