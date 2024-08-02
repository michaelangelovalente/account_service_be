package com.beije.account_service_be.system_global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCodeExceptionEnum {

    ER001("Internal error", HttpStatus.BAD_REQUEST),
    ER002("ID not found", HttpStatus.BAD_REQUEST),
    ER003("Validation exception", HttpStatus.BAD_REQUEST),
    ER004("Invalid or expired token", HttpStatus.UNAUTHORIZED),
    ER005("Invalid user or password", HttpStatus.BAD_REQUEST),
    ER006("Invalid user role", HttpStatus.BAD_REQUEST),
    ER007("Duplicate record", HttpStatus.BAD_REQUEST),
    ER008("User is disabled", HttpStatus.BAD_REQUEST),
    ER009("Invalid token", HttpStatus.UNAUTHORIZED),
    ER010("Email already confirmed", HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus httpStatus;

    private ErrorCodeExceptionEnum(String message) {
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
    }

    private ErrorCodeExceptionEnum(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
