package com.hc.kugou.service.exception.songsheet;

/**
 * @author ck
 * @create 2019-05-21 14:24
 */
public class SongSheetNameException extends SongSheetException{
    public SongSheetNameException() {
        super();
    }

    public SongSheetNameException(String message) {
        super(message);
    }

    public SongSheetNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public SongSheetNameException(Throwable cause) {
        super(cause);
    }

    protected SongSheetNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
