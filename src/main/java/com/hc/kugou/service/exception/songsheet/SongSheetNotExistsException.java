package com.hc.kugou.service.exception.songsheet;

/**
 * @author ck
 * @create 2019-05-15 14:53
 */
public class SongSheetNotExistsException extends SongSheetException {
    public SongSheetNotExistsException() {
        super();
    }

    public SongSheetNotExistsException(String message) {
        super(message);
    }

    public SongSheetNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SongSheetNotExistsException(Throwable cause) {
        super(cause);
    }

    protected SongSheetNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
