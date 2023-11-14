package com.compassuol.sp.challenge.msuser.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getFieldName(), request.getDescription(false));
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private static class ApiError {
        private final HttpStatus status;
        private final String message;
        private final String fieldName;
        private final String path;

        public ApiError(HttpStatus status, String message, String fieldName, String path) {
            this.status = status;
            this.message = message;
            this.fieldName = fieldName;
            this.path = path;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getPath() {
            return path;
        }
    }
}
