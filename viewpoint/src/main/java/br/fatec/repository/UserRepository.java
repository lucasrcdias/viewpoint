package br.fatec.repository;

import org.springframework.data.repository.CrudRepository;

import br.fatec.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
