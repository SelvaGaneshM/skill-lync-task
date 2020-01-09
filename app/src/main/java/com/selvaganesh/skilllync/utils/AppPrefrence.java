package com.selvaganesh.skilllync.utils;

import android.content.Context;

import com.selvaganesh.skilllync.AppController;
import com.selvaganesh.skilllync.custom.EncryptedPreferences;


public class AppPrefrence {

    private String TAG = AppPrefrence.class.getSimpleName();
    private EncryptedPreferences userencryptedPreferences;

    private String AppUserName = "App_User_Name";

    public AppPrefrence(Context context) {
        AppController.Injector.inject(this);


        userencryptedPreferences = new EncryptedPreferences.Builder(context)
                .withPreferenceName(AppConstant.APP_PREFERENCE).withEncryptionPassword("db_user_preference").build();
    }

    public void clearPreference() {
        userencryptedPreferences.edit().clear();
    }

    public void setObjectId(String objectId) {
        userencryptedPreferences.edit().putString(AppUserName, objectId).apply();
    }

    public String getObjectId() {
        return userencryptedPreferences.getString(AppUserName, "");
    }




}
