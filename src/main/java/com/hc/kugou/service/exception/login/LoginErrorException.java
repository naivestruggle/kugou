package com.hc.kugou.service.exception.login;

/**
 * @author ck
 * @create 2019-05-14 12:57
 */
public class LoginErrorException extends LoginException {
    public LoginErrorException() {
        super();
    }

    public LoginErrorException(String message) {
        super(message);
    }

    public LoginErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginErrorException(Throwable cause) {
        super(cause);
    }

    protected LoginErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
