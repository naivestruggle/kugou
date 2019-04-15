package com.hc.kugou.service.exception.password;

import com.hc.kugou.service.exception.CustomException;

/**
 * @author ck
 * @create 2019-05-13 14:54
 */
public class PasswordException extends Exception implements CustomException {
    public PasswordException() {
        super();
    }

    public PasswordException(String message) {
        super(message);
    }

    public PasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordException(Throwable cause) {
        super(cause);
    }

    protected PasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
