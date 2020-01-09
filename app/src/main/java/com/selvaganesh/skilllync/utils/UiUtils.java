package com.selvaganesh.skilllync.utils;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UiUtils {

    private static Toast mToast;

    public static void appErrorLog(String tag, String msg) {
        try {
            Log.e(tag, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(Context context, String input) {
        try {
            if (!TextUtils.isEmpty(input)) {
                if (Build.VERSION.SDK_INT >= 28) {
                    mToast = Toast.makeText(context, input, Toast.LENGTH_SHORT);
                    mToast.setGravity(Gravity.CENTER, 0, 0);
                    mToast.show();
                } else {
                    mToast = Toast.makeText(context, input, Toast.LENGTH_SHORT);
                    mToast.setGravity(Gravity.CENTER, 0, 0);
                    CountDownTimer toastCountDown;
                    int toastDurationInMilliSeconds = 1000;
                    toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 100) { /*Tick duration*/
                        public void onTick(long millisUntilFinished) {
                            mToast.show();
                        }

                        public void onFinish() {
                            mToast.cancel();
                        }
                    };
                    mToast.show();
                    toastCountDown.start();
                }
            }
        } catch (Exception e) {
            UiUtils.appErrorLog(TAG, "Toast Ex : " + e.getMessage());
        }
    }

    public static void appLog(String tag, String msg) {
        try {
                Log.v(tag, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showFailureToast(Context context, String input) {
        UiUtils.appLog(TAG, "Failure Toast : " + TextUtils.getString(input));
        try {
            if (!TextUtils.isEmpty(input)) {
                if (Build.VERSION.SDK_INT >= 28) {
                    mToast = Toast.makeText(context, input, Toast.LENGTH_SHORT);
                    mToast.setGravity(Gravity.CENTER, 0, 0);
                    mToast.show();
                } else {
                    mToast = Toast.makeText(context, input, Toast.LENGTH_SHORT);
                    mToast.setGravity(Gravity.CENTER, 0, 0);
                    mToast.show();
                }
            }
        } catch (Exception e) {
            UiUtils.appErrorLog(TAG, "Failure Toast Ex : " + e.getMessage());
        }
    }
}
