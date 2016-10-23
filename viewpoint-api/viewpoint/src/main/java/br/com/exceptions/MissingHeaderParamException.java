package br.com.exceptions;

import org.springframework.http.HttpStatus;

public class MissingHeaderParamException extends BusinessException {

    public MissingHeaderParamException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
