package com.hc.kugou.service.exception.songsheet;

/**
 * @author ck
 * @create 2019-05-15 14:57
 */
public class SongSheetExistsException extends SongSheetException {
    public SongSheetExistsException() {
        super();
    }

    public SongSheetExistsException(String message) {
        super(message);
    }

    public SongSheetExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SongSheetExistsException(Throwable cause) {
        super(cause);
    }

    protected SongSheetExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
