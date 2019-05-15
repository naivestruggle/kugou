package com.hc.kugou.service.exception.songsheet;

/**
 * @author ck
 * @create 2019-05-15 15:29
 */
public class SongSheetAddException extends SongSheetException {
    public SongSheetAddException() {
        super();
    }

    public SongSheetAddException(String message) {
        super(message);
    }

    public SongSheetAddException(String message, Throwable cause) {
        super(message, cause);
    }

    public SongSheetAddException(Throwable cause) {
        super(cause);
    }

    protected SongSheetAddException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
