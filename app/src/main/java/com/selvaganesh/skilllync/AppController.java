package com.selvaganesh.skilllync;

import android.app.Application;

import com.selvaganesh.skilllync.base.DaggerMyComponent;
import com.selvaganesh.skilllync.base.MyComponent;
import com.selvaganesh.skilllync.models.CommonModel;
import com.selvaganesh.skilllync.models.NetModule;

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
