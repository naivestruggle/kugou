package com.hc.kugou.service.exception.password;

/**
 * @author ck
 * @create 2019-05-13 14:50
 */
public class UnknownPasswordException extends PasswordException{
    public UnknownPasswordException() {
        super();
    }

    public UnknownPasswordException(String message) {
        super(message);
    }

    public UnknownPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownPasswordException(Throwable cause) {
        super(cause);
    }

    protected UnknownPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
