package com.loy.limits.config;

import com.loy.limits.config.properties.LimitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LimitProperties.class)
public class LimitConfig {
}
