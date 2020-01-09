package com.selvaganesh.skilllync.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.selvaganesh.skilllync.db.entry.CallLogs;

import java.util.List;


@Dao
public interface CallLogsDao {

    @Query("SELECT * FROM CallLogs")
    List<CallLogs> getAllCallLogs();

    @Insert
    void insert(CallLogs callLogs);

    @Delete
    void delete(CallLogs callLogs);

    @Update
    void update(CallLogs callLogs);
}
