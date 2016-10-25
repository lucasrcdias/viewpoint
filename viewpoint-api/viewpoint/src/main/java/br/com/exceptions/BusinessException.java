package br.com.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final HttpStatus status;
    private final String message;
    private final String field;


    public BusinessException(HttpStatus status, String field, String message) {
        this.status = status;
        this.field = field;
        this.message = message;
    }


    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }
}