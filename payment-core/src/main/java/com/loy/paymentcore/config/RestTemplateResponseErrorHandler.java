package com.loy.paymentcore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loy.paymentcore.dto.ErrorResponseDto;
import com.loy.paymentcore.exception.IntegrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        ErrorResponseDto errorResponseDto = objectMapper.readValue(response.getBody(), ErrorResponseDto.class);
        if (response.getStatusCode().is4xxClientError()) {
            throw new IntegrationException("Ошибка при обращении к внешней api: " + errorResponseDto.message(), errorResponseDto.timestamp());
        }
        if (response.getStatusCode().is5xxServerError()) {
            throw new IntegrationException("Ошибка на стороне внешней api: " + errorResponseDto.message(), errorResponseDto.timestamp());
        }
    }

}
