package com.selvaganesh.karadipathinterview.repro;

import com.selvaganesh.karadipathinterview.response.productlist.ProductListResposen;
import com.selvaganesh.karadipathinterview.utils.ApiConstant;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface AppRepoApi {

    @POST(ApiConstant.PRODUCT_LIST)
    Observable<ProductListResposen> getProductList();

}
