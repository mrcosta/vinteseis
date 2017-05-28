package com.vinteseis.challenge.exceptions;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(value =  { MethodArgumentNotValidException.class, HttpMessageNotReadableException.class} )
    @ResponseStatus(value = NO_CONTENT)
    protected void handleValidationException() {}
}
