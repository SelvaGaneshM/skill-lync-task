package com.selvaganesh.skilllync.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.selvaganesh.skilllync.db.dao.CallLogsDao;
import com.selvaganesh.skilllync.db.entry.CallLogs;


@Database(entities = {CallLogs.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CallLogsDao callLogsDao();
}
