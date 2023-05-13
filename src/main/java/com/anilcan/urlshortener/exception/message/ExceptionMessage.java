package com.anilcan.urlshortener.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
    INVALID_URL_EXCEPTION("Given URL is invalid.", "invalid-url", HttpStatus.BAD_REQUEST),
    URL_NOT_FOUND_EXCEPTION("URL not found with given short URL", "url-not-found", HttpStatus.NOT_FOUND),
    UNKNOWN_EXCEPTION("Unknown error occurred.", "unknown-error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final String errorName;
    private final HttpStatus errorCode;
}
