package com.hc.kugou.service.exception.login;

import com.hc.kugou.service.exception.CustomException;

/**
 * @author ck
 * @create 2019-05-14 12:57
 */
public class LoginException extends Exception implements CustomException {
    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    protected LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
