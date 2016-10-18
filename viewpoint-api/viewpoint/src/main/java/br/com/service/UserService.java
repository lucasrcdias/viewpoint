package br.com.service;

import br.com.model.UserRepository;
import br.com.model.entity.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User create(String email) {
		User userByKey = getUserRepository().findOneByEmail(email);
		if (userByKey != null) {
			throw new RuntimeException("the user has already registred");
		}
		userByKey = new User();
		userByKey.setKey(createKey(email));
		userByKey.setEmail(email);
		return getUserRepository().save(userByKey);
	}

	public User update(Long id, String email) throws NotFoundException {
		User userByKey = getUserRepository().findOne(id);
		if (userByKey == null) {
			throw new NotFoundException("User not found by id");
		}
		userByKey.setEmail(email);
		userByKey.setKey(createKey(email));
		return getUserRepository().save(userByKey);
	}

	public void delete(Long id) throws NotFoundException {
		User userByKey = getUserRepository().findOne(id);
		if (userByKey == null) {
			throw new NotFoundException("User not found by id");
		}
		getUserRepository().delete(userByKey);
	}

	private String createKey(String email) {
		return String.valueOf(email.hashCode());
	}

	public User findByEmail(String email) {
		return getUserRepository().findOneByEmail(email);
	}

	public User findByKey(String key) {
		return getUserRepository().findOneByKey(key);
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
