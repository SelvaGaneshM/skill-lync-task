package com.selvaganesh.skilllync.repro;


public class AppRepo {
    private String TAG = AppRepo.class.getSimpleName();
    private AppRepoApi appRepoApi;

    public AppRepo(AppRepoApi appRepoApi) {
        this.appRepoApi = appRepoApi;
    }

}
