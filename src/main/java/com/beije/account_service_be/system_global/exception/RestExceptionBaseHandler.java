package com.beije.account_service_be.system_global.exception;

import com.beije.account_service_be.system_global.domain.dto.response.InvalidResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class RestExceptionBaseHandler extends BaseExceptionHandler {


    @ExceptionHandler({ GenericErrorCodeException.class })
    public ResponseEntity<InvalidResponseDto> handleGenericErrorCodeException(Exception ex, WebRequest request) {
        logger.error("handleGenericErrorCodeException", ex);
        GenericErrorCodeException exception = (GenericErrorCodeException) ex;

        final InvalidResponseDto baseResponse =  InvalidResponseDto
                .builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .status(exception.getHttpStatus())
                .error(exception.getCodeException().name())
                .path(extractPathFromUri(request))
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(baseResponse);
    }



//    @ExceptionHandler({ Exception.class })
//    public ResponseEntity<Object> handleDefaultException(Exception ex, WebRequest request) {
//        logger.error("handleDefaultException", ex);
//
//        HttpStatus status = null;
//        BaseResponse baseResponse = null;
//
//        try {
//            String exStr = ex.getMessage();
//            String statusCode = exStr.substring(exStr.lastIndexOf("{\"result\":{\"error\""), exStr.length()-1);
//            baseResponse = new ObjectMapper().readValue(statusCode, BaseResponse.class);
//            status = HttpStatus.BAD_REQUEST;
//        } catch (Exception e) {
//            baseResponse = new BaseResponse();
//            baseResponse.getResult().setErrorCode(ErrorCodeExceptionEnum.ER001.name());
//            baseResponse.getResult().setError(true);
//            baseResponse.getResult().setErrorMessage(ex.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//
//
//        return ResponseEntity.status(status).body(baseResponse);
//    }


}
