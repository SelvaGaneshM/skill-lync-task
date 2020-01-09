package com.selvaganesh.skilllync.exceptions;

import java.io.IOException;

public class LimitReachedException extends IOException {

    public String statusCode;
    public String message;
    public String retryAfter;

    public LimitReachedException(String detailMessage) {
        super(detailMessage);
    }

    public static LimitReachedException create(String RetryAfter, String message, String statusCode) throws IOException {
        LimitReachedException limitReachedException = new LimitReachedException(message);
        limitReachedException.message = message;
        limitReachedException.statusCode = statusCode;
        limitReachedException.retryAfter = RetryAfter;
        return limitReachedException;
    }
}

