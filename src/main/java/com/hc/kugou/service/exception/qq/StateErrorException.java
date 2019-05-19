package com.hc.kugou.service.exception.qq;

/**
 * @author ck
 * @create 2019-05-19 20:07
 */
public class StateErrorException extends QQException {
    public StateErrorException() {
        super();
    }

    public StateErrorException(String message) {
        super(message);
    }

    public StateErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public StateErrorException(Throwable cause) {
        super(cause);
    }

    protected StateErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
