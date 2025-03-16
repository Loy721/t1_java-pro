package com.loy.paymentcore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.loy.paymentcore.config.property.IntegrationsTemplateProps;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

@Configuration
@EnableConfigurationProperties(IntegrationsTemplateProps.class)
@RequiredArgsConstructor
public class IntegrationConf {
    private final IntegrationsTemplateProps integrationsTemplateProps;

    @Bean
    public RestTemplate userProductRestTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        return new RestTemplateBuilder().rootUri(integrationsTemplateProps.getRestTemplateProps().getUri())
                .setReadTimeout(integrationsTemplateProps.getRestTemplateProps().getConnectTimeout())
                .setConnectTimeout(integrationsTemplateProps.getRestTemplateProps().getReadTimeout())
                .errorHandler(restTemplateResponseErrorHandler)
                .build();
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
