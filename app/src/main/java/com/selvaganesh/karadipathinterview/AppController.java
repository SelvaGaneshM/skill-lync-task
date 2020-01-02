package com.selvaganesh.karadipathinterview;

import android.app.Application;

import com.selvaganesh.karadipathinterview.base.DaggerMyComponent;
import com.selvaganesh.karadipathinterview.base.MyComponent;
import com.selvaganesh.karadipathinterview.models.CommonModel;
import com.selvaganesh.karadipathinterview.models.NetModule;

public class AppController extends Application {

    public static MyComponent Injector;
    public static AppController instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Injector = DaggerMyComponent.builder()
                .commonModel(new CommonModel(this))
                .netModule(new NetModule(this))
                .build();
        instance = this;
    }


}
