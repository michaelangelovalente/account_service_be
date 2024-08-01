package com.beije.account_service_be.system_global.exception;

import com.beije.account_service_be.system_global.domain.dto.response.InvalidResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
@RequiredArgsConstructor
public class BaseExceptionHandler {


    @ExceptionHandler({ConstraintDeclarationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<InvalidResponseDto> handleValidationException(ConstraintDeclarationException e, @NotNull HttpServletRequest request) {
        ExceptionCodesEnum badReq = ExceptionCodesEnum.E001;
        return ResponseEntity.status(badReq.getHttpStatus()).body(
                InvalidResponseDto.builder()
                        .error(badReq.getMessage())
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build()
        );
    }
}
