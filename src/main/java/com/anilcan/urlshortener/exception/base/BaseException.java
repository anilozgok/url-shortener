package com.anilcan.urlshortener.exception.base;

import com.anilcan.urlshortener.exception.message.ExceptionMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException {
    protected final ExceptionMessage exceptionMessage;
}
