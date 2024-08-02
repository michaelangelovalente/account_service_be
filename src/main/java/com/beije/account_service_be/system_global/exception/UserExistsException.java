package com.beije.account_service_be.system_global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Duplication: user already exists")
public class UserExistsException extends RestExceptionBaseHandler{
}
