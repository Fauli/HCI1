package com.example.hci1.arztpraxis.service;

/**
 * Thrown in case an error occurs within {@link DiaryController}.
 */
public class DiaryControllerException extends RuntimeException {
    public DiaryControllerException() {
    }

    public DiaryControllerException(String message) {
        super(message);
    }

    public DiaryControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiaryControllerException(Throwable cause) {
        super(cause);
    }

    public DiaryControllerException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
