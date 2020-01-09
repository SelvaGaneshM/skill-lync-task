package com.selvaganesh.skilllync.db.entry;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CallLogs implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;

    @ColumnInfo(name = "callType")
    private String callType;

    @ColumnInfo(name = "callDate")
    private String callDate;

    @ColumnInfo(name = "callDayTime")
    private String callDayTime;

    @ColumnInfo(name = "callDuration")
    private String callDuration;

    @ColumnInfo(name = "callMode")
    private String callMode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getCallDayTime() {
        return callDayTime;
    }

    public void setCallDayTime(String callDayTime) {
        this.callDayTime = callDayTime;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getCallMode() {
        return callMode;
    }

    public void setCallMode(String callMode) {
        this.callMode = callMode;
    }
}
