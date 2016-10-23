package br.com.service;

import br.com.exceptions.BusinessException;
import br.com.exceptions.UserNotFoundException;
import br.com.BusinessException;
import br.com.model.UserRepository;
import br.com.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(String email, String password, String name) {
        User user = getUserRepository().findOneByEmail(email);
        if (user != null) {
            throw new BusinessException(HttpStatus.ALREADY_REPORTED, "O e-mail informado já está em uso, utilize o link para recuperação de senha");
        }
        user = new User();
        user.setKey(createKey(email));
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        return getUserRepository().save(user);
    }

    public User update(Long id, String email, String password, String name) {
        User user = getUserRepository().findOne(id);
        if (user == null) {
            throw new UserNotFoundException("Usuário não encontrado.");
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

    public void delete(Long id) {
        User userByKey = getUserRepository().findOne(id);
        if (userByKey == null) {
            throw new UserNotFoundException("Usuário não encontrado.");
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

    public boolean authenticate(String authToken) {
        User user = findByKey(authToken);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("Usuário não encontrado pela chave");
        }
        return true;
    }
}
