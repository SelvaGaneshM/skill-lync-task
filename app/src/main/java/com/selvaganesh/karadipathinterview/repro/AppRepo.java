package com.selvaganesh.karadipathinterview.repro;

import com.selvaganesh.karadipathinterview.response.productlist.ProductListResposen;
import com.selvaganesh.karadipathinterview.utils.RxJavaUtils;

import io.reactivex.Observable;

public class AppRepo {
    private String TAG = AppRepo.class.getSimpleName();
    private AppRepoApi appRepoApi;

    public AppRepo(AppRepoApi appRepoApi) {
        this.appRepoApi = appRepoApi;
    }

    public Observable<ProductListResposen> getProductList() {
        return appRepoApi.getProductList()
                .compose(RxJavaUtils.applyErrorTransformer())
                .map(submitEnquiryFormResponse -> submitEnquiryFormResponse);
    }
}
