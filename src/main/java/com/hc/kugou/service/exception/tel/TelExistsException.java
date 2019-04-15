package com.hc.kugou.service.exception.tel;

/**
 * @author ck
 * @create 2019-05-13 20:22
 */
public class TelExistsException extends TelException {
    public TelExistsException() {
        super();
    }

    public TelExistsException(String message) {
        super(message);
    }

    public TelExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelExistsException(Throwable cause) {
        super(cause);
    }

    protected TelExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
