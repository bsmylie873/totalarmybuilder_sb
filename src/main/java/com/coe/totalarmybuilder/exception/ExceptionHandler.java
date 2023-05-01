package com.coe.totalarmybuilder.exception;

import com.coe.totalarmybuilder.exception.custom.ResourceNotFoundException;
import com.coe.totalarmybuilder.exception.utility.ErrorResponse;
import com.coe.totalarmybuilder.exception.utility.ExceptionBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(final Exception exception) {
        return ExceptionBuilder.buildErrorResponseRepresentation(HttpStatus.NO_CONTENT, exception.getMessage());
    }
}