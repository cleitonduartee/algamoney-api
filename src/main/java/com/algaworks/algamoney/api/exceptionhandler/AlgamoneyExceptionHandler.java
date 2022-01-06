package com.algaworks.algamoney.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String BAD_REQUEST = "BAD_REQUEST";
    private static final String NOT_FOUND = "NOT_FOUND";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String err = "Campos inválidos";
        StandartError error = StandartError.builder()
                .error(BAD_REQUEST)
                .status(status.value())
                .timestamp(Instant.now())
                .message(err)
                .build();
        return handleExceptionInternal(ex,error,headers,status,request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(erro ->{
            String campo = ((FieldError)erro).getField();
            String err = erro.getDefaultMessage();
            errors.put(campo,err);
        });


        String err = "Campos inválidos";
        StandartErrorMap error = StandartErrorMap.builder()
                .message(err)
                .timestamp(Instant.now())
                .error(BAD_REQUEST)
                .errors(errors)
                .status(status.value())
                .build();
        return handleExceptionInternal(ex,error,headers,status,request);
    }
}
