package br.com.model;

import org.springframework.data.repository.CrudRepository;

import br.com.model.entity.Action;

import java.util.List;

public interface ActionRepository extends CrudRepository<Action, Long>{

    List<Action> findAllByGroup(String group);

    List<Action> findAllByName(String name);
}
