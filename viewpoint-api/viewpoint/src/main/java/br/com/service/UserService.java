package br.com.service;

import br.com.model.UserRepository;
import br.com.model.entity.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(String email, String password, String name) {
        User user = getUserRepository().findOneByEmail(email);
        if (user != null) {
            throw new RuntimeException("the user has already registered");
        }
        user = new User();
        user.setKey(createKey(email));
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        return getUserRepository().save(user);
    }

    public User update(Long id, String email, String password, String name) throws NotFoundException {
        User user = getUserRepository().findOne(id);
        if (user == null) {
            throw new NotFoundException("User not found by id");
        }
        if (Objects.nonNull(email)) {
            user.setEmail(email);
            user.setKey(createKey(email));
        }
        if (Objects.nonNull(password)) {
            user.setEmail(password);
        }
        if (Objects.nonNull(name)) {
            user.setEmail(name);
        }
        return getUserRepository().save(user);
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
