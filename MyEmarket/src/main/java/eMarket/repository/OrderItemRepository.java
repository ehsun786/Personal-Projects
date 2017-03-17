package eMarket.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import eMarket.domain.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
	List<OrderItem> findById(int id);
}