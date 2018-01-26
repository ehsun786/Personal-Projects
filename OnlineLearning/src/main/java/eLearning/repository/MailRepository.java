package eLearning.repository;

import org.springframework.data.repository.CrudRepository;

import eLearning.domain.Mail;

public interface MailRepository extends CrudRepository<Mail, Integer> {
	Mail findBymailId(int id);
}
