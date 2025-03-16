package com.loy.paymentcore.api;

import com.loy.paymentcore.dto.ErrorResponseDto;
import com.loy.paymentcore.exception.IntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IntegrationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleIntegrationException(IntegrationException integrationException) {
        return new ErrorResponseDto(integrationException.getMessage(), integrationException.getTimestamp());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto  handleIntegrationException(Exception e) {
        return new ErrorResponseDto(e.getMessage(), LocalDateTime.now());
    }
}
