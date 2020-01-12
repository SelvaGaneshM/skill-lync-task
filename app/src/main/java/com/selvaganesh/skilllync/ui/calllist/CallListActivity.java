package com.selvaganesh.skilllync.ui.calllist;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selvaganesh.skilllync.R;
import com.selvaganesh.skilllync.base.BaseActivity;
import com.selvaganesh.skilllync.db.DatabaseClient;
import com.selvaganesh.skilllync.db.entry.CallLogs;
import com.selvaganesh.skilllync.service.TelemanagerService;
import com.selvaganesh.skilllync.utils.UiUtils;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallListActivity extends BaseActivity {

    @BindView(R.id.ryc_product_list)
    RecyclerView rycProductList;

    private String TAG = CallListActivity.class.getSimpleName();
    private CallListAdaprtor adaprtor;
    private List<CallLogs> callLogs = new ArrayList<>();
    private static final int REQUEST_CODE = 0;
    private DevicePolicyManager mDPM;
    private ComponentName mAdminName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        setUI();
        getDBDatas();
    }

    private void setUI() {
        rycProductList.setLayoutManager(new GridLayoutManager(this, 1));
        adaprtor = new CallListAdaprtor(this);
        rycProductList.setAdapter(adaprtor);
        try {
            mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
            mAdminName = new ComponentName(this, DeviceAdminDemo.class);

            if (!mDPM.isAdminActive(mAdminName)) {
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Click on Activate button to secure your application.");
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                // mDPM.lockNow();
                 Intent intent = new Intent(this, TelemanagerService.class);
                startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDBDatas() {
        new AsyncTask<Void, Void, List<CallLogs>>() {
            @Override
            protected List<CallLogs> doInBackground(Void... params) {
                return DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .callLogsDao().getAllCallLogs();
            }

            @Override
            protected void onPostExecute(List<CallLogs> callLogsData) {
                callLogs.clear();
                callLogs.addAll(callLogsData);
                UiUtils.appLog(TAG, "" + callLogs.size());
                adaprtor.setData(callLogs);
            }
        }.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == requestCode) {
            Intent intent = new Intent(this, TelemanagerService.class);
            startService(intent);
        }
    }
}
