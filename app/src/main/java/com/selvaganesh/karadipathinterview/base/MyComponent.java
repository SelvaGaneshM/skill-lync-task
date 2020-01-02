package com.selvaganesh.karadipathinterview.base;

import com.selvaganesh.karadipathinterview.models.CommonModel;
import com.selvaganesh.karadipathinterview.models.NetModule;
import com.selvaganesh.karadipathinterview.utils.AppPrefrence;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CommonModel.class, NetModule.class})
public interface MyComponent {

    void inject(BaseActivity baseActivity);

    void inject(AppPrefrence appPrefrence);

    void inject(BaseViewModel baseViewModel);
}