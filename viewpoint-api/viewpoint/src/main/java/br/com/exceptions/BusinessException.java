package br.com.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -5184277815781893663L;

    private final HttpStatus status;
    private final String message;
    private final MultiValueMap<String, String> headers;

    public BusinessException(HttpStatus status, String errorMessage, Exception e) {
        super(errorMessage, e);
        this.status = status;
        this.message = errorMessage;
        this.headers = null;
    }

    public BusinessException(HttpStatus status, String errorMessage) {
        this(status, errorMessage, null);
    }


    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public MultiValueMap<String, String> getHeaders() {
        return headers;
    }
}