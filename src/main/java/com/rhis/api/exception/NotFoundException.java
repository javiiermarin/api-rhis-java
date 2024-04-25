package com.rhis.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase personalizada para manejar la excepciones de tipo NotFound
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException{

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.getReasonPhrase(), message);
    }
}
