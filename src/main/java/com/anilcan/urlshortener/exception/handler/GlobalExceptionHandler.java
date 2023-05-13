package com.anilcan.urlshortener.exception.handler;

import com.anilcan.urlshortener.exception.base.BaseException;
import com.anilcan.urlshortener.exception.message.ExceptionMessage;
import com.anilcan.urlshortener.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    private ResponseEntity<ErrorResponse> handle(BaseException e) {
        log.error("Error occurred. Error: ", e);
        return new ResponseEntity<>(new ErrorResponse(e.getExceptionMessage(), LocalDateTime.now()),
                                    e.getExceptionMessage().getErrorCode());
    }
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponse> handle(Exception e) {
        log.error("Error occurred. Error: ", e);
        return new ResponseEntity<>(new ErrorResponse(ExceptionMessage.UNKNOWN_EXCEPTION, LocalDateTime.now()),
                ExceptionMessage.UNKNOWN_EXCEPTION.getErrorCode());
    }
}
