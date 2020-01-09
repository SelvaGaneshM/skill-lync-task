package com.selvaganesh.skilllync.exceptions;

import retrofit2.HttpException;
import retrofit2.Response;

public class ApiHttpException extends HttpException {
    public ApiHttpException(Response<?> response) {
        super(response);
    }
}
