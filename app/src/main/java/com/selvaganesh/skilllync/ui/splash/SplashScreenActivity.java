package com.selvaganesh.skilllync.ui.splash;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.CallLog;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.selvaganesh.skilllync.R;
import com.selvaganesh.skilllync.base.BaseActivity;
import com.selvaganesh.skilllync.db.entry.CallLogs;
import com.selvaganesh.skilllync.ui.calllist.CallListActivity;
import com.selvaganesh.skilllync.utils.NetworkUtils;
import com.selvaganesh.skilllync.utils.UiUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends BaseActivity {

    @BindView(R.id.splash_screen_loader)
    LottieAnimationView splashScreenLoader;

    private String TAG = SplashScreenActivity.class.getSimpleName();
    private SplashScreenViewModel viewModel;
    private String phNumber = "", callType = "", callDate = "", callDuration = "", status = "";
    private Date callDayTime;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    private void invokeApiCall(int whichApi, int timeDelay) {
        Message msgObj = apiCallHandler.obtainMessage();
        Bundle b = new Bundle();
        b.putInt("message", whichApi);
        msgObj.setData(b);
        apiCallHandler.sendMessageDelayed(msgObj, timeDelay);
    }

    private final Handler apiCallHandler = new Handler() {
        public void handleMessage(Message message) {
            int apiCall = message.getData().getInt("message");
            if (NetworkUtils.isConnected()) {
                switch (apiCall) {
                    case 1:
                        break;
                }
            } else {
                viewModel.showFailureMessage("No Internet Connection!..");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        viewModel = new SplashScreenViewModel();
        viewModel.setContext(this);
        splashScreenLoader.playAnimation();
        setObservable();
        invokeApiCall(1, 0);
        if (checkAndRequestPermissions()) {
            getCallDetails();
        }
    }

    private void setObservable() {

    }

    private  boolean checkAndRequestPermissions() {
        int readCallLogPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
        int writeCallLogPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (readCallLogPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CALL_LOG);
        }
        if (writeCallLogPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_CALL_LOG);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    private void getCallDetails() {
        try {
            Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
            int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
            while (managedCursor.moveToNext()) {
                phNumber = managedCursor.getString(number); // mobile number
                callType = managedCursor.getString(type); // call type
                callDate = managedCursor.getString(date); // call date
                callDayTime = new Date(Long.valueOf(callDate));
                callDuration = managedCursor.getString(duration);
                status = null;
                int dircode = Integer.parseInt(callType);
                switch (dircode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        status = "OUTGOING";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        status = "INCOMING";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        status = "MISSED";
                        break;
                }

                CallLogs callLogs = new CallLogs();
                callLogs.setPhoneNumber(phNumber);
                callLogs.setCallType(callType);
                callLogs.setCallDate(callDate);
                callLogs.setCallDuration(callDuration);
                callLogs.setCallDate(callDayTime.toString());
                callLogs.setCallMode(status);

                SaveCallLogs st = new SaveCallLogs(this, callLogs);
                st.execute();
            }
            managedCursor.close();
            moveToNextActivity();
        } catch (Exception ex) {
            UiUtils.appErrorLog(TAG, ex.toString());
            moveToNextActivity();
        }
    }

    private void moveToNextActivity() {
        startActivity(new Intent(this, CallListActivity.class));
        this.finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.d(TAG, "Permission callback called-------");
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {

                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put(Manifest.permission.WRITE_CALL_LOG, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_CALL_LOG, PackageManager.PERMISSION_GRANTED);
                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(Manifest.permission.WRITE_CALL_LOG) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                        if (checkAndRequestPermissions()) {
                            getCallDetails();
                        }
                    } else {
                        Log.d(TAG, "Some permissions are not granted ask again ");
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CALL_LOG)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CALL_LOG)) {
                            showDialogOK("Service Permissions are required for this app",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    checkAndRequestPermissions();
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // proceed with logic by disabling the related features or quit the app.
                                                    finish();
                                                    break;
                                            }
                                        }
                                    });
                        }
                    }
                }
            }
        }

    }


    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }
}
