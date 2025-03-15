package com.loy.paymentcore.config.property;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;


@ConfigurationProperties(prefix = "integration")
public class IntegrationsTemplateProps {
    private final RestTemplateProps restTemplateProps;

    public RestTemplateProps getRestTemplateProps() {
        return restTemplateProps;
    }

    @ConstructorBinding
    public IntegrationsTemplateProps(RestTemplateProps restTemplateProps) {
        this.restTemplateProps = restTemplateProps;
    }
}
