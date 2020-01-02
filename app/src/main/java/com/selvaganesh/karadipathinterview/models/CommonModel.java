package com.selvaganesh.karadipathinterview.models;

import com.selvaganesh.karadipathinterview.AppController;
import com.selvaganesh.karadipathinterview.utils.AppPrefrence;
import com.selvaganesh.karadipathinterview.utils.UiUtils;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModel {

    private AppController instnce;

    public CommonModel(AppController instnce) {
        this.instnce = instnce;
    }

    @Provides
    @Singleton
    UiUtils getUiUtils() {
        return new UiUtils();
    }

    @Provides
    @Singleton
    AppPrefrence getAppPref() {
        return new AppPrefrence(instnce);
    }
}
