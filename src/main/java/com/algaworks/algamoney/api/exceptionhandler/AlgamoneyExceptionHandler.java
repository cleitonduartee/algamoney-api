package com.algaworks.algamoney.api.exceptionhandler;

import com.algaworks.algamoney.api.model.exception.RecursoNaoEncontrado;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AlgamoneyExceptionHandler{

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String err = "Campo(s) inválido(s)";
        StandartError error = StandartError.builder()
                .error(err)
                .status(status.value())
                .timestamp(Instant.now())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity(error,status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(erro ->{
            String campo = ((FieldError)erro).getField();
            String err = erro.getDefaultMessage();
            errors.put(campo,err);
        });

        String err = "Erro de validação";
        StandartErrorMap error = StandartErrorMap.builder()
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .error(err)
                .errors(errors)
                .path(request.getRequestURI())
                .status(status.value())
                .build();
        return new ResponseEntity(error,status);
    }

    @ExceptionHandler(RecursoNaoEncontrado.class)
    public ResponseEntity<StandartError> handleRecursoNaoEncontrado(RecursoNaoEncontrado ex, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        String err = "Código não encontrado.";
        StandartError error = StandartError.builder()
                .status(status.value())
                .timestamp(Instant.now())
                .error(err)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(error);
    }
}
