package com.loy.paymentcore.exception;

import java.time.LocalDateTime;

public class IntegrationException extends RuntimeException {
    private LocalDateTime timestamp;

    public IntegrationException(String message, LocalDateTime timestamp) {
        super(message);
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
