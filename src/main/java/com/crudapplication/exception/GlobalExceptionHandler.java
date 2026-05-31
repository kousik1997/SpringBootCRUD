package com.crudapplication.exception;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import java.util.HashMap;

import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)

    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(

            EmployeeNotFoundException ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()

                .message(ex.getMessage())

                .status(HttpStatus.NOT_FOUND.value())

                .timestamp(LocalDateTime.now())

                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<Map<String, String>> handleValidationException(

            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {

            errors.put(error.getField(), error.getDefaultMessage());

        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)

    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()

                .message(ex.getMessage())

                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())

                .timestamp(LocalDateTime.now())

                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
 