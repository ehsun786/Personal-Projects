package eLearning.repository;

import org.springframework.data.repository.CrudRepository;

import eLearning.domain.ChartData;

public interface ChartDataRepository extends CrudRepository<ChartData, Integer> {
	ChartData findById(int id);
}
