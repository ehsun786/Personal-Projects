package eLearning.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eLearning.domain.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
	
	@Query(value = "SELECT * FROM course", nativeQuery = true)
	public ArrayList<Course> findAll();
	
	Course findBycourseId(int id);
}
