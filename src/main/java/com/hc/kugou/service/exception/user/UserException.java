package com.hc.kugou.service.exception.user;

import com.hc.kugou.service.exception.CustomException;

/**
 * @author ck
 * @create 2019-05-13 14:56
 */
public class UserException extends Exception implements CustomException {
    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    protected UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
