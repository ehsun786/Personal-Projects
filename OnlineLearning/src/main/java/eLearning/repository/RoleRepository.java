package eLearning.repository;

import org.springframework.data.repository.CrudRepository;

import eLearning.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	public Role findByroleId(int id);
}
