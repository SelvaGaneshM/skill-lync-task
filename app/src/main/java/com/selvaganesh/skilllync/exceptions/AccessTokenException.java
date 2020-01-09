package com.selvaganesh.skilllync.exceptions;

import java.io.IOException;

import retrofit2.Response;

public class AccessTokenException extends IOException {

    public AccessTokenException(String detailMessage) {
        super(detailMessage);
    }

    public static AccessTokenException create(Response response) throws IOException {
        return new AccessTokenException(response.errorBody().string());
    }

    public static AccessTokenException create(String errorbody) {
        return new AccessTokenException(errorbody);
    }
}
