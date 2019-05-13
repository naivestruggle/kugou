package com.hc.kugou.service.exception.user;

/**
 * @author ck
 * @create 2019-05-13 14:45
 */
public class UnknownUserException extends UserException {
    public UnknownUserException() {
        super();
    }

    public UnknownUserException(String message) {
        super(message);
    }

    public UnknownUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownUserException(Throwable cause) {
        super(cause);
    }

    protected UnknownUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
