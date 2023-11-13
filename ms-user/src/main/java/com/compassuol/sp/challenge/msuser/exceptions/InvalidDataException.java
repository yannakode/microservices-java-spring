package com.compassuol.sp.challenge.msuser.exceptions;

public class InvalidDataException extends RuntimeException {

    private final String fieldName;

    public InvalidDataException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }


}
