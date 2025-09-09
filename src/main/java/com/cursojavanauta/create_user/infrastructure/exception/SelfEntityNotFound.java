package com.cursojavanauta.create_user.infrastructure.exception;

public class SelfEntityNotFound extends RuntimeException {
    public SelfEntityNotFound(String message) {
        super(message);
    }

    public SelfEntityNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
