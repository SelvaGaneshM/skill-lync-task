package com.selvaganesh.karadipathinterview.exceptions;

import com.selvaganesh.karadipathinterview.AppController;
import com.selvaganesh.karadipathinterview.utils.UiUtils;

public class ThrowableHandler {

    private String TAG = ThrowableHandler.class.getSimpleName();

    public void onError(String message) {
        UiUtils.showFailureToast(AppController.instance, message);
    }

    public void exceptionHandling(Throwable throwable) {
        try {
            if (throwable instanceof AccessTokenException) {
                if (throwable.getMessage().matches("(?i).*CertPathValidatorException.*")) {
                } else {
                    UiUtils.appErrorLog(TAG, "Refresh Token Failed 1");
                }
            } else if (throwable instanceof ResouceNotFoundException) {
                onError(throwable.getMessage());
            }
        } catch (Exception ex) {
            try {
                UiUtils.appLog(TAG, ex.toString());
            } catch (Exception exx) {
                UiUtils.appLog(TAG, exx.toString());
            }
        }
    }


}
