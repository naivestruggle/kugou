package com.hc.kugou.service.exception.music;

/**
 * @Author:
 * @Date:2019/4/19
 * @Description:com.hc.kugou.service.exception.music
 * @Version:1.0
 */
public class NotFoundMusicException extends MusicException {
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public NotFoundMusicException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NotFoundMusicException(String message) {
        super(message);
    }
}
