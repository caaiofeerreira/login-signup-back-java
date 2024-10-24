package com.login_auth.infra.exception;

public class ValidateException extends RuntimeException {

    public ValidateException(String message) {
        super(message);
    }
}
