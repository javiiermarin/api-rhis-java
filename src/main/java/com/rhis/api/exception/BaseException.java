package com.rhis.api.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase personalizada para las excepciones
 */
@Setter
@Getter
public class BaseException extends Exception{
    private final String httpStatus;
    private final String message;

    public BaseException(String httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
