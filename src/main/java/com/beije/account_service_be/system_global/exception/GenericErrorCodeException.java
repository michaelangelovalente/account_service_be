package com.beije.account_service_be.system_global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericErrorCodeException extends RuntimeException {

    private final ErrorCodeExceptionEnum codeException;
    private final HttpStatus httpStatus;

    public GenericErrorCodeException(ErrorCodeExceptionEnum codeException, String message, HttpStatus httpStatus) {
        super(message);
        this.codeException = codeException;
        this.httpStatus = httpStatus;
    }

//    public GenericErrorCodeException(ErrorCodeExceptionEnum codeException, HttpStatus httpStatus) {
//        this(codeException, codeException.getMessage(), httpStatus);
//    }
//
//
//
    public GenericErrorCodeException(ErrorCodeExceptionEnum codeException, String message) {
        this(codeException, message, HttpStatus.NOT_ACCEPTABLE);
    }
//
//    public GenericErrorCodeException(ErrorCodeExceptionEnum codeException) {
//        this(codeException, codeException.getMessage(), codeException.getHttpStatus());
//
//    }

    public GenericErrorCodeException(String validation) {
        super(validation);
        this.codeException = ErrorCodeExceptionEnum.ER003;
        this.httpStatus = codeException.getHttpStatus();
    }

}
