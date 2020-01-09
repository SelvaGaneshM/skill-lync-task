package com.selvaganesh.skilllync.ui.calllist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selvaganesh.skilllync.R;
import com.selvaganesh.skilllync.db.entry.CallLogs;
import com.selvaganesh.skilllync.utils.UiUtils;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallListAdaprtor extends RecyclerView.Adapter<CallListAdaprtor.MyViewHolder> {

    private String TAG = CallListAdaprtor.class.getSimpleName();
    private Context context;
    private List<CallLogs> callLogs = new ArrayList<>();

    public CallListAdaprtor(Context context) {
        this.context = context;
    }

    public void setData(List<CallLogs> callLogs) {
        if (callLogs == null && callLogs.size() == 0) {
            return;
        }
        this.callLogs.clear();
        this.callLogs.addAll(callLogs);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            CallLogs callLog = callLogs.get(position);
            holder.txtPhoneNumber.setText(callLog.getPhoneNumber());
            holder.txtCallType.setText("Call Type : " + callLog.getCallMode());
            holder.txtCallDate.setText("Call Date : " + callLog.getCallDate());
            holder.txtCallDate.setSelected(true);
            holder.txtCallDayTime.setText("Call Day Time : " + callLog.getCallDayTime());
            holder.txtCallDuration.setText("Call Duration : " + callLog.getCallDuration());
        } catch (Exception ex) {
            UiUtils.appLog(TAG, ex.toString());
        }
    }

    @Override
    public int getItemCount() {
        return callLogs.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_phoneNumber)
        TextView txtPhoneNumber;
        @BindView(R.id.txt_callType)
        TextView txtCallType;
        @BindView(R.id.txt_callDate)
        TextView txtCallDate;
        @BindView(R.id.txt_callDayTime)
        TextView txtCallDayTime;
        @BindView(R.id.txt_callDuration)
        TextView txtCallDuration;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
