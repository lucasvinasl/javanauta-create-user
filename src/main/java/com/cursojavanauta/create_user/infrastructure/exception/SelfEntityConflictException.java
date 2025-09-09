package com.cursojavanauta.create_user.infrastructure.exception;

public class SelfEntityConflictException extends RuntimeException {
    public SelfEntityConflictException(String message) {
        super(message);
    }

    public SelfEntityConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
