package com.cursojavanauta.create_user.infrastructure.exception;

public class SelfConflictException extends RuntimeException {
    public SelfConflictException(String message) {
        super(message);
    }

    public SelfConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
