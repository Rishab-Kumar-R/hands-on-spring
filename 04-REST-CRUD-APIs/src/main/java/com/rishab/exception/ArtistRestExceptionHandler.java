package com.rishab.exception;

import com.rishab.controller.ArtistErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Global exception handler for ArtistRestController
@ControllerAdvice
public class ArtistRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ArtistErrorResponse> handleException(ArtistNotFoundException ex) {
        ArtistErrorResponse error = new ArtistErrorResponse();

        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ArtistErrorResponse> handleException(Exception ex) {
        ArtistErrorResponse error = new ArtistErrorResponse();

        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
