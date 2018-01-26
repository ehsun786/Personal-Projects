package eLearning.repository;

import org.springframework.data.repository.CrudRepository;

import eLearning.domain.Chat;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
	Chat findById(int id);
}
