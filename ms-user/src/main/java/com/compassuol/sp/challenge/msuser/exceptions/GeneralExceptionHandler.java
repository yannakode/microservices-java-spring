package com.compassuol.sp.challenge.msuser.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<UserErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        UserErrorResponse error = new UserErrorResponse();

        error.setCode(HttpStatus.CONFLICT.value());
        error.setStatus(HttpStatus.CONFLICT.name());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<UserErrorResponse> handleInvalidDataException(InvalidDataException ex) {
        UserErrorResponse error = new UserErrorResponse();

        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setStatus(HttpStatus.BAD_REQUEST.name());
        error.setMessage(ex.getMessage());
        error.setDetails(new DetailsUserErrorResponse(ex.getFieldName(), ex.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
