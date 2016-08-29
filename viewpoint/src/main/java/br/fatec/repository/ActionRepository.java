package br.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.fatec.model.Action;

public interface ActionRepository extends CrudRepository<Action, Long> {

	@Query("from Action where group = (?1)")
	List<Action> findOneByGroup(String group);
}
