package com.selvaganesh.skilllync.ui.calllist;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selvaganesh.skilllync.R;
import com.selvaganesh.skilllync.base.BaseActivity;
import com.selvaganesh.skilllync.db.DatabaseClient;
import com.selvaganesh.skilllync.db.entry.CallLogs;
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
}
