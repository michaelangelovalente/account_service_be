package com.beije.account_service_be.system_global.exception;

import com.beije.account_service_be.system_global.domain.dto.response.InvalidResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class RestExceptionBaseHandler extends BaseExceptionHandler {


    @ExceptionHandler({GenericErrorCodeException.class})
    public ResponseEntity<InvalidResponseDto> handleGenericErrorCodeException(Exception ex, WebRequest request) {
        logger.error("handleGenericErrorCodeException", ex);
        GenericErrorCodeException exception = (GenericErrorCodeException) ex;

        final InvalidResponseDto baseResponse = InvalidResponseDto
                .builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .status((long) exception.getHttpStatus().value())
                .error(exception.getCodeException().name())
                .path(extractPathFromUri(request))
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(baseResponse);
    }

//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ResponseEntity<GenericErrorResponseDto> handleBadCredentialsException(Exception ex, WebRequest request) {
//        logger.error("handleBadCredentialsException", ex);
//
//        final GenericErrorResponseDto genericErrorResponseDto =
//                GenericErrorResponseDto.builder()
//                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
//                        .status((long) HttpStatus.UNAUTHORIZED.value())
//                        .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
//                        .message("")
//                        .path(extractPathFromUri(request))
//                        .build();
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(genericErrorResponseDto);
//    }



}
