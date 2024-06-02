package com.rhis.api.exception;

public class UserConflictException extends ConflictException {

    public UserConflictException() {
        super("User already exists");
    }
}
