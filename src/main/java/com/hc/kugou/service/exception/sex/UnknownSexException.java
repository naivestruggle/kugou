package com.hc.kugou.service.exception.sex;

/**
 * @author ck
 * @create 2019-05-13 15:02
 */
public class UnknownSexException extends SexException {

    public UnknownSexException() {
        super();
    }

    public UnknownSexException(String message) {
        super(message);
    }

    public UnknownSexException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownSexException(Throwable cause) {
        super(cause);
    }

    protected UnknownSexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
