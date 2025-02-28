package br.com.pedrooliveira.rocket_project.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String msg) {
        super(msg);
    }
}
