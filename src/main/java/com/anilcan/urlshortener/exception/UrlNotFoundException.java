package com.anilcan.urlshortener.exception;

import com.anilcan.urlshortener.exception.base.BaseException;
import com.anilcan.urlshortener.exception.message.ExceptionMessage;

public class UrlNotFoundException extends BaseException {
    public UrlNotFoundException() {
        super(ExceptionMessage.URL_NOT_FOUND_EXCEPTION);
    }
}
