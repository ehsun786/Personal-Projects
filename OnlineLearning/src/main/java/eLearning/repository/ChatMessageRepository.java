package eLearning.repository;

import org.springframework.data.repository.CrudRepository;

import eLearning.domain.ChatMessage;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Integer> {
	ChatMessage findById(int id);
}
