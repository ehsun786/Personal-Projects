package eLearning.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eLearning.domain.Learner;
import eLearning.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByUsername(String login);
	public User findByEmail(String email);
	public void removeByusername(String login);
}
