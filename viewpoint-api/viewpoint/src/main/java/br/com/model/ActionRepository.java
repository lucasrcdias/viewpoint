package br.com.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.model.entity.Action;

import java.util.List;

public interface ActionRepository extends CrudRepository<Action, Long> {

    @Query("from Action a where a.user.id=?1 and a.group=?2")
    List<Action> findAllByGroupAndUser(Long userId, String group);

    List<Action> findAllByName(String name);

    @Query("from Action a where a.user.id=?1")
    List<Action> findAllGroupByUser(Long userId);
}
