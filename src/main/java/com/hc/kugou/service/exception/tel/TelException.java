package com.hc.kugou.service.exception.tel;

import com.hc.kugou.service.exception.CustomException;

/**
 * @author ck
 * @create 2019-05-13 14:55
 */
public class TelException extends Exception implements CustomException {
    public TelException() {
        super();
    }

    public TelException(String message) {
        super(message);
    }

    public TelException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelException(Throwable cause) {
        super(cause);
    }

    protected TelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
