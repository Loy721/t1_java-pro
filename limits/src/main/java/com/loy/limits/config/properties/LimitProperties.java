package com.loy.limits.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "limit")
@Getter
public class LimitProperties {
    private Long defaultLimit;

    @ConstructorBinding
    public LimitProperties(Long defaultLimit) {
        this.defaultLimit = defaultLimit;
    }
}
