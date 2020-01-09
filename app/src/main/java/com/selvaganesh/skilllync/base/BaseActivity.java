package com.selvaganesh.skilllync.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.selvaganesh.skilllync.AppController;
import com.selvaganesh.skilllync.R;
import com.selvaganesh.skilllync.utils.AppPrefrence;
import com.selvaganesh.skilllync.utils.UiUtils;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    @Inject
    UiUtils uiUtils;

    @Inject
    AppPrefrence appPrefrence;

    private String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        AppController.Injector.inject(this);
        appPrefrence.setObjectId("Selva Ganesh M");
        Log.e(TAG, appPrefrence.getObjectId());
    }
}
