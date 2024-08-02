package com.beije.account_service_be.system_global.exception;

import com.beije.account_service_be.system_global.domain.dto.response.InvalidResponseDto;
import io.micrometer.common.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
@NonNullApi
public abstract class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        GenericErrorCodeException genericException = new GenericErrorCodeException(ex.getMessage());

        var invalidResponseBody = InvalidResponseDto.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .status( (long)genericException.getHttpStatus().value()) // 400
                .error(genericException.getHttpStatus().getReasonPhrase())
                .path(extractPathFromUri(request))
                .build();
        return Objects.requireNonNull(handleExceptionInternal(ex, invalidResponseBody, headers, status, request));
    }



    protected String extractPathFromUri(WebRequest webRequest){
        String requestUrl = webRequest.getDescription(false);

        requestUrl = requestUrl.replaceFirst("uri=", "");

        String path = null;
        try {
            URI uri = new URI(requestUrl);
            path = uri.getPath();
        } catch (URISyntaxException e) {
            throw new GenericErrorCodeException( ErrorCodeExceptionEnum.ER001, "Invalid URI PATH " + e.getMessage());
        }
        return path;
    }



}
