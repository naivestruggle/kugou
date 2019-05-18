package com.hc.kugou.service.exception.music;

/**
 * @author ck
 * @create 2019-05-17 9:09
 */
public class MusicExistsException extends MusicException {
    public MusicExistsException() {
        super();
    }

    public MusicExistsException(String message) {
        super(message);
    }

    public MusicExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MusicExistsException(Throwable cause) {
        super(cause);
    }

    protected MusicExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
