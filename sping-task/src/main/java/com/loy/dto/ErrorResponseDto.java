package com.loy.dto;

import java.time.LocalDateTime;

public record ErrorResponseDto(String message, LocalDateTime timestamp) {}
