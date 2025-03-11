package com.loy.paymentcore.config.property;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;


@ConfigurationProperties(prefix = "integration")
public class IntegrationsTemplateProps {
    public RestTemplateProps getRestTemplateProps() {
        return restTemplateProps;
    }

    private final RestTemplateProps restTemplateProps;

    @ConstructorBinding
    public IntegrationsTemplateProps(RestTemplateProps restTemplateProps) {
        this.restTemplateProps = restTemplateProps;
    }
}
