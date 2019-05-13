package com.hc.kugou.service.exception.verify;

import com.hc.kugou.service.exception.CustomException;

/**
 * @author ck
 * @create 2019-05-13 14:57
 */
public class VerifyException extends Exception implements CustomException {
    public VerifyException() {
        super();
    }

    public VerifyException(String message) {
        super(message);
    }

    public VerifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifyException(Throwable cause) {
        super(cause);
    }

    protected VerifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
