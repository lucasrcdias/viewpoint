package br.com.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String field, String message) {
        super(HttpStatus.NOT_FOUND, field, message);
    }

}
