package com.hc.kugou.service.exception.email;

import com.hc.kugou.service.exception.CustomException;

/**
 * @author ck
 * @create 2019-05-14 10:24
 */
public class EmailException extends Exception implements CustomException {

    public EmailException() {
        super();
    }

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailException(Throwable cause) {
        super(cause);
    }

    protected EmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
