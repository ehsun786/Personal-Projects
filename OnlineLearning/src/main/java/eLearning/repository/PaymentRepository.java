package eLearning.repository;

import org.springframework.data.repository.CrudRepository;

import eLearning.domain.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
	Payment findBypaymentId(int id);
}
