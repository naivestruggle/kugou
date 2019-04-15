package com.hc.kugou.service.exception.email;

/**
 * @author ck
 * @create 2019-05-14 10:25
 */
public class UnknownEmailException extends EmailException {
    public UnknownEmailException() {
        super();
    }

    public UnknownEmailException(String message) {
        super(message);
    }

    public UnknownEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownEmailException(Throwable cause) {
        super(cause);
    }

    protected UnknownEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
