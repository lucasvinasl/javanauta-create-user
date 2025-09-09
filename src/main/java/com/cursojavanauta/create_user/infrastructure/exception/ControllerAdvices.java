package com.cursojavanauta.create_user.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvices {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SelfEntityConflictException.class)
    public Map<String, String> handleValidationExceptions(
            SelfEntityConflictException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(SelfEntityNotFound.class)
    public Map<String, String> handleEntityNotFoundException(SelfEntityNotFound ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(SelfLoginAuthException.class)
    public Map<String, String> handleLoginAuthException(SelfLoginAuthException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message","Usuário ou senha inválida.");
        return errors;
    }

}
