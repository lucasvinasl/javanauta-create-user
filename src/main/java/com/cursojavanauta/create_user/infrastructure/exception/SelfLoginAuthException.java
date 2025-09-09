package com.cursojavanauta.create_user.infrastructure.exception;


import org.springframework.security.core.AuthenticationException;

public class SelfLoginAuthException extends AuthenticationException {

    public SelfLoginAuthException(String message) {
        super(message);
    }

    public SelfLoginAuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
