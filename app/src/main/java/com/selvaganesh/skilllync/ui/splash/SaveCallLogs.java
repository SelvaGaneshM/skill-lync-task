package com.selvaganesh.skilllync.ui.splash;

import android.content.Context;
import android.os.AsyncTask;

import com.selvaganesh.skilllync.db.DatabaseClient;
import com.selvaganesh.skilllync.db.entry.CallLogs;
import com.selvaganesh.skilllync.utils.UiUtils;

public class SaveCallLogs extends AsyncTask<Void, Void, Void> {

    private String TAG = SaveCallLogs.class.getSimpleName();
    private Context context;
    private CallLogs callLogs;


    public SaveCallLogs(Context context, CallLogs callLogs) {
        this.context = context;
        this.callLogs = callLogs;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DatabaseClient.getInstance(context).getAppDatabase()
                .callLogsDao()
                .insert(callLogs);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        UiUtils.appLog(TAG, "Saved");
    }
}
