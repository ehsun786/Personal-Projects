package eLearning.repository;

import org.springframework.data.repository.CrudRepository;

import eLearning.domain.SubTopic;

public interface SubTopicRepository extends CrudRepository<SubTopic, Integer> {
	SubTopic findBysubtopicId(int id);
}
