package com.loy.paymentcore;

import com.loy.paymentcore.config.property.IntegrationsTemplateProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(IntegrationsTemplateProps.class)
public class PaymentCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentCoreApplication.class, args);
    }

}
