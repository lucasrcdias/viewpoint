package br.com.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("from User u where u.key=?1")
	User findOneByKey(String key);

	User findOneByEmail(String email);

}
