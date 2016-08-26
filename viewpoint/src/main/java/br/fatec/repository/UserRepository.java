package br.fatec.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.fatec.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
