package com.beije.account_service_be.system_global.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionCodesEnum {

    E001(HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus httpStatus;

    private ExceptionCodesEnum(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }

    private ExceptionCodesEnum(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        this.message = httpStatus.getReasonPhrase();

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

}
