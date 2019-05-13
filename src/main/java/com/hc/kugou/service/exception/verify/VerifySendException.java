package com.hc.kugou.service.exception.verify;

/**
 * @author ck
 * @create 2019-05-13 20:26
 */
public class VerifySendException extends VerifyException {
    public VerifySendException() {
        super();
    }

    public VerifySendException(String message) {
        super(message);
    }

    public VerifySendException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifySendException(Throwable cause) {
        super(cause);
    }

    protected VerifySendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
