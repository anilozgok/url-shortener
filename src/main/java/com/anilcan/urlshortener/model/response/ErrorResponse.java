package com.anilcan.urlshortener.model.response;

import com.anilcan.urlshortener.exception.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(ExceptionMessage exceptionMessage, LocalDateTime occurredAt) {
}
