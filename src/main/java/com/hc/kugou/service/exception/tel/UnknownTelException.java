package com.hc.kugou.service.exception.tel;

/**
 * @author ck
 * @create 2019-05-13 14:48
 */
public class UnknownTelException extends TelException {
    public UnknownTelException() {
        super();
    }

    public UnknownTelException(String message) {
        super(message);
    }

    public UnknownTelException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownTelException(Throwable cause) {
        super(cause);
    }

    protected UnknownTelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
