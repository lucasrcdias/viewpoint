package br.com.model;

import br.com.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	User findOneByKey(String key);

	User findOneByEmail(String email);

}
