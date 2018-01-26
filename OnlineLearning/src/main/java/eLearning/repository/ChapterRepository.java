package eLearning.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eLearning.domain.Chapter;


public interface ChapterRepository extends CrudRepository<Chapter, Integer> {
	@Query(value = "SELECT * FROM chapter", nativeQuery = true)
	public ArrayList<Chapter> findAll();
	Chapter findBychapterId(int id);
}
