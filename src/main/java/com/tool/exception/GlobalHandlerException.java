package com.tool.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleRuntimeException(Exception e) {
        return ExceptionResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
    }
    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleJsonProcessingException(Exception e) {
        return ExceptionResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
    }
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleBindException(BindException e) {
        String msg = "";
        if (e.getBindingResult().hasErrors()) {
            msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        }
        return ExceptionResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .message(msg)
                .build();
    }
}
