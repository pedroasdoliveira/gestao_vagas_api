package br.com.pedrooliveira.rocket_project.exceptions;

import br.com.pedrooliveira.rocket_project.exceptions.DTO.ErrorMessageDTO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController extends RuntimeException {

    private final MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());

            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
