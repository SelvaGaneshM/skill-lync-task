package com.selvaganesh.karadipathinterview.ui.splash;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.selvaganesh.karadipathinterview.base.BaseViewModel;
import com.selvaganesh.karadipathinterview.response.productlist.ProductListResposen;
import com.selvaganesh.karadipathinterview.utils.RxJavaUtils;

public class SplashScreenViewModel extends BaseViewModel {

    MutableLiveData<ProductListResposen> productsResponseMLD = new MutableLiveData<>();
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void getProducts() {
        showLoading(context);
        appRepo.getProductList()
                .compose(RxJavaUtils.applyObserverSchedulers())
                .subscribe(presenterResponse -> {
                    productsResponseMLD.setValue(presenterResponse);
                    hideLoading();
                }, throwable -> {
                    hideLoadExceptionHandling(throwable);
                });
    }

    private void hideLoadExceptionHandling(Throwable throwable) {
        hideLoading();
        exceptionHandling(throwable);
    }

}
