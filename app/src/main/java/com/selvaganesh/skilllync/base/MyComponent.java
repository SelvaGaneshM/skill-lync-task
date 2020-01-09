package com.selvaganesh.skilllync.base;

import com.selvaganesh.skilllync.models.CommonModel;
import com.selvaganesh.skilllync.models.NetModule;
import com.selvaganesh.skilllync.utils.AppPrefrence;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CommonModel.class, NetModule.class})
public interface MyComponent {

    void inject(BaseActivity baseActivity);

    void inject(AppPrefrence appPrefrence);

    void inject(BaseViewModel baseViewModel);
}