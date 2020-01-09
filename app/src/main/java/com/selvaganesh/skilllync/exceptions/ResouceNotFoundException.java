package com.selvaganesh.skilllync.exceptions;

import java.io.IOException;

import retrofit2.Response;

public class ResouceNotFoundException extends IOException {

    public ResouceNotFoundException(String detailMessage) {
        super(detailMessage);
    }

    public static ResouceNotFoundException create(Response response) throws IOException {
        return new ResouceNotFoundException(response.message());
    }

}
