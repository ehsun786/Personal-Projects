package eLearning.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eLearning.domain.Administrator;

public interface AdminRepository extends CrudRepository<Administrator, Integer> {
	
	//@Query(value = "SELECT * FROM administrator", nativeQuery = true)
	//public ArrayList<Administrator> findAll();
	
	public Administrator findByusername(String username);
	public void deleteByusername(String username);
}
