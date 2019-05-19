package com.hc.kugou.service.exception.qq;

import com.hc.kugou.service.exception.CustomException;

/**
 * @author ck
 * @create 2019-05-19 20:06
 */
public class QQException extends Exception implements CustomException {
    public QQException() {
        super();
    }

    public QQException(String message) {
        super(message);
    }

    public QQException(String message, Throwable cause) {
        super(message, cause);
    }

    public QQException(Throwable cause) {
        super(cause);
    }

    protected QQException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
