package com.hc.kugou.service.exception.register;

/**
 * @author ck
 * @create 2019-05-13 15:34
 */
public class RegisterErrorException extends RegisterException {

    public RegisterErrorException() {
        super();
    }

    public RegisterErrorException(String message) {
        super(message);
    }

    public RegisterErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterErrorException(Throwable cause) {
        super(cause);
    }

    protected RegisterErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
