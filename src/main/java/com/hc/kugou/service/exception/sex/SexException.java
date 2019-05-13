package com.hc.kugou.service.exception.sex;

import com.hc.kugou.service.exception.CustomException;

/**
 * @author ck
 * @create 2019-05-13 15:00
 */
public class SexException extends Exception implements CustomException {
    public SexException() {
        super();
    }

    public SexException(String message) {
        super(message);
    }

    public SexException(String message, Throwable cause) {
        super(message, cause);
    }

    public SexException(Throwable cause) {
        super(cause);
    }

    protected SexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
