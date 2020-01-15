package com.ehsun.eventsavy.repos;

import org.springframework.data.repository.CrudRepository;

import com.ehsun.eventsavy.entities.User;

public interface UserRepository extends CrudRepository<User, String> {

	public User findByUsername(String login);
	public User findByEmail(String email);
	public void removeByusername(String login);
	
}
