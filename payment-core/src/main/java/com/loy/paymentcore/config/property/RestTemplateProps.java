package com.loy.paymentcore.config.property;

import java.time.Duration;

public class RestTemplateProps {
    private String uri;
    private Duration connectTimeout;
    private Duration readTimeout;

    public String getUri() {
        return uri;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setReadTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
    }
}
