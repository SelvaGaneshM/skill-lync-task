package com.selvaganesh.karadipathinterview.exceptions;

import retrofit2.HttpException;
import retrofit2.Response;

public class ApiHttpException extends HttpException {
    public ApiHttpException(Response<?> response) {
        super(response);
    }
}
