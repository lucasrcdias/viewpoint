package br.fatec.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.fatec.model.Action;

@Repository
public interface ActionRepository extends CrudRepository<Action, Long> {

}
