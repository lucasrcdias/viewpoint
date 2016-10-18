package br.com.model;

import org.springframework.data.repository.CrudRepository;

import br.com.model.entity.Action;

public interface ActionRepository extends CrudRepository<Action, Long>{

}
