package com.hc.kugou.service.exception.verify;

/**
 * @author ck
 * @create 2019-05-13 14:49
 */
public class UnknownVerifyException extends VerifyException {
    public UnknownVerifyException() {
        super();
    }

    public UnknownVerifyException(String message) {
        super(message);
    }

    public UnknownVerifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownVerifyException(Throwable cause) {
        super(cause);
    }

    protected UnknownVerifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
