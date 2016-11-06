package br.com.service;

import br.com.JwtUtils;
import br.com.exceptions.BusinessException;
import br.com.exceptions.UserNotFoundException;
import br.com.model.UserRepository;
import br.com.model.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
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
        user.setPassword(passwordEncoder(password));
        user.setName(name);
        user.setKey(String.valueOf(email.hashCode()));
        user.setToken(jwtUtils.generateToken(user));
        return getUserRepository().save(user);
    }

    private String passwordEncoder(String password) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "password", "Erro ao criptografar senha");
        }
    }

    public User login(String email, String password) throws JsonProcessingException {
        User user = getUserRepository().findOneByEmailAndPassword(email, passwordEncoder(password));
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("Login", "Usuário não encontrado pelo email e senha, por favor confira suas credenciais");
        }
        user.setToken(jwtUtils.generateToken(user));
        return user;
    }

    public User update(String email, String password, String name, String token) throws JsonProcessingException {
        User user = tokenValidation(token);
        if (Objects.nonNull(password)) {
            user.setPassword(password);
        }
        if (Objects.nonNull(name)) {
            user.setName(name);
        }
        if (Objects.nonNull(email)) {
            user.setEmail(email);
            user.setKey(String.valueOf(email.hashCode()));
            user.setToken(jwtUtils.generateToken(user));
        }
        return getUserRepository().save(user);
    }

    public void delete(String token) {
        User user = tokenValidation(token);
        getUserRepository().delete(user);
    }

    public User findOneByEmail(String email) {
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

    public User tokenValidation(String token) {
        User user = getUserByClaims(token);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("Authorization", "Usuário não encontrado pela chave");
        }
        return user;
    }

    private User getUserByClaims(String token) {
        String jwt = token.substring("Bearer ".length());
        Claims claims = JwtUtils.getClaims(jwt);
        if (claims.getSubject() != null) {
            return userRepository.findOneByEmail(claims.getSubject());
        }
        return null;
    }

    public boolean authenticate(String authToken) {
        String jwt = authToken.substring("Bearer ".length());
        if (!jwtUtils.isAuthenticated(jwt)) {
            throw new UserNotFoundException("Authorization", "Usuário não encontrado pela chave");
        }
        return true;
    }

    public User findOneByKey(String key) {
        return userRepository.findOneByKey(key);
    }

    public void passwordRecovery(String email) throws EmailException {
        User user = findOneByEmail(email);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("Email", "Usuário não encontrado pelo email");
        }

        String newPassword = RandomStringUtils.randomAlphabetic(16);
        user = updatePassword(user, newPassword);

        String emailMessage = "Olá " + user.getName() + ", foi solicitada uma nova senha para o email: "
                + user.getEmail() + ", Sua nova senha é: " + newPassword;
        SendEmailService.sendEmail(user.getEmail(), user.getName(), "Solicitação de Recuperação de Senha", emailMessage);
    }

    private User updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder(newPassword));
        return userRepository.save(user);
    }

}
