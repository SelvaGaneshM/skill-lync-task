package com.selvaganesh.skilllync.exceptions;


import java.io.IOException;

import retrofit2.Response;


public class AppException extends IOException {


    public AppException(String detailMessage) {
        super(detailMessage);
    }

    public static boolean isBofinException(Response response) {
        return response.code() >= 400 && response.code() <= 500;
    }

    public static boolean isAccessTokenException(int errorCode) {
        return errorCode == 401;
    }

    public static boolean isRefreshTokenException(int errorCode) {
        return errorCode == 403;
    }

    public static AppException create(Response response) throws IOException {
        return new AppException(response.errorBody().string());
    }
}
