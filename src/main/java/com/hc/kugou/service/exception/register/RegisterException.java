package com.hc.kugou.service.exception.register;

import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.service.exception.CustomException;

/**
 * @author ck
 * @create 2019-05-13 15:33
 */
public class RegisterException extends Exception implements CustomException {

    public RegisterException() {
        super();
    }

    public RegisterException(String message) {
        super(message);
    }

    public RegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterException(Throwable cause) {
        super(cause);
    }

    protected RegisterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
