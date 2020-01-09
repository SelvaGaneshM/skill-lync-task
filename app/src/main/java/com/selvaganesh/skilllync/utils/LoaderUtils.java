package com.selvaganesh.skilllync.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;

import com.selvaganesh.skilllync.R;


public class LoaderUtils {
    public MutableLiveData<String> dialogText = new MutableLiveData();
    private String TAG = LoaderUtils.class.getSimpleName();
    private Dialog dialog = null;
    private TextView descriptionText;


    public LoaderUtils() {
        dialogText.observeForever(s -> {
            if (dialog != null && dialog.isShowing() && descriptionText != null) {
                String text = !TextUtils.isEmpty(s)
                        ? s : TextUtils.getString("Please wait for a moment!...");
                descriptionText.setText(text);
            }
        });

    }


    public void showLoadingDialog(Context context, boolean isCancelable, String inputMessage) {
        if (dialog == null) {
            createDialog(context, isCancelable, inputMessage);
        } else if (dialog != null && !dialog.isShowing()) {
            if (descriptionText != null) {
                descriptionText.setText("Please wait for a moment!...");
            }
            dialog.show();
        }
    }

    public void dismissDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
            UiUtils.appErrorLog(TAG, e.getMessage());
        }
    }


    private void createDialog(Context context, boolean isCancelable, String inputMessage) {
        try {
            dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(isCancelable);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(R.layout.activity_loader);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            descriptionText = dialog.findViewById(R.id.txt_submit_waiting);
            descriptionText.setText("Please wait for a moment!...");
            dialog.show();
            // if use fragments or not use this
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = dialog.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
            UiUtils.appErrorLog(TAG, "show Loading Alert some thing wrong : " + e);
        }
    }
}
