package br.com.service;

import br.com.JwtUtils;
import br.com.exceptions.BusinessException;
import br.com.exceptions.UserNotFoundException;
import br.com.model.UserRepository;
import br.com.model.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    public User create(String email, String password, String name) throws JsonProcessingException {
        User user = getUserRepository().findOneByEmail(email);
        if (user != null) {
            throw new BusinessException(HttpStatus.ALREADY_REPORTED, "email", "O e-mail informado já está em uso, utilize o link para recuperação de senha");
        }
        user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setKey(jwtUtils.generateToken(user));
        return getUserRepository().save(user);
    }

    public User update(String email, String password, String name, String token) throws JsonProcessingException {
        String jwt = token.substring("Bearer ".length());
        User user = getUserRepository().findOneByKey(jwt);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("Authorization", "Usuário não encontrado pela chave");
        }
        if (Objects.nonNull(password)) {
            user.setEmail(password);
        }
        if (Objects.nonNull(name)) {
            user.setEmail(name);
        }
        if (Objects.nonNull(email)) {
            user.setEmail(email);
            user.setKey(jwtUtils.generateToken(user));
        }
        return getUserRepository().save(user);
    }


    public void delete(String token) {
        String jwt = token.substring("Bearer ".length());
        User user = getUserRepository().findOneByKey(jwt);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("Authorization", "Usuário não encontrado pela chave");
        }
        getUserRepository().delete(user);
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
        String jwt = authToken.substring("Bearer ".length());
        if (!jwtUtils.isAuthenticated(jwt)) {
            throw new UserNotFoundException("Authorization", "Usuário não encontrado pela chave");
        }
        return true;
    }
}
