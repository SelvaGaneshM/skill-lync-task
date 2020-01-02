package com.selvaganesh.karadipathinterview.base;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.selvaganesh.karadipathinterview.AppController;
import com.selvaganesh.karadipathinterview.exceptions.ThrowableHandler;
import com.selvaganesh.karadipathinterview.repro.AppRepo;
import com.selvaganesh.karadipathinterview.utils.LoaderUtils;
import com.selvaganesh.karadipathinterview.utils.UiUtils;

import javax.inject.Inject;

public class BaseViewModel extends ViewModel {

    @Inject
    public AppRepo appRepo;

    private String TAG = BaseViewModel.class.getSimpleName();
    private ThrowableHandler throwableHandler;
    private LoaderUtils loaderUtils;

    public BaseViewModel() {
        AppController.Injector.inject(this);
        throwableHandler = new ThrowableHandler();
        loaderUtils = new LoaderUtils();
    }

    public void showFailureMessage(String msg) {
        UiUtils.showFailureToast(AppController.instance, msg);
    }

    protected void exceptionHandling(Throwable throwable) {
        throwableHandler.exceptionHandling(throwable);
    }

    public void showLoading(Context context) {
        showLoad(context, false, "");
    }

    public void showLoading(Context context, String message) {
        showLoad(context, false, message);
    }

    private void showLoad(Context context, boolean cancel, String inputString) {
        loaderUtils.showLoadingDialog(context, cancel, inputString);
    }

    public void hideLoading() {
        loaderUtils.dismissDialog();
    }
}
