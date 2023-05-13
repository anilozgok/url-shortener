package com.anilcan.urlshortener.exception;

import com.anilcan.urlshortener.exception.base.BaseException;
import com.anilcan.urlshortener.exception.message.ExceptionMessage;

public class InvalidUrlException extends BaseException {

    public InvalidUrlException(){
        super(ExceptionMessage.INVALID_URL_EXCEPTION);
    }
}
