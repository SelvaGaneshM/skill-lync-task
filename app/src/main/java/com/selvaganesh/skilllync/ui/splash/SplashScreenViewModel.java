package com.selvaganesh.skilllync.ui.splash;

import android.content.Context;

import com.selvaganesh.skilllync.base.BaseViewModel;

public class SplashScreenViewModel extends BaseViewModel {

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void getAPICall() {
        showLoading(context);

    }

    private void hideLoadExceptionHandling(Throwable throwable) {
        hideLoading();
        exceptionHandling(throwable);
    }

}
