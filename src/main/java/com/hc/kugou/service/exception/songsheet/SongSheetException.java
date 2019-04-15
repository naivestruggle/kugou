package com.hc.kugou.service.exception.songsheet;

import com.hc.kugou.service.exception.CustomException;

/**
 * @author ck
 * @create 2019-05-15 14:52
 */
public class SongSheetException extends Exception implements CustomException {
    public SongSheetException() {
        super();
    }

    public SongSheetException(String message) {
        super(message);
    }

    public SongSheetException(String message, Throwable cause) {
        super(message, cause);
    }

    public SongSheetException(Throwable cause) {
        super(cause);
    }

    protected SongSheetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
