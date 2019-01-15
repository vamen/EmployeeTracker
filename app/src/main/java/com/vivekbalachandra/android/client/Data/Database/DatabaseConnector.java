package com.vivekbalachandra.android.client.Data.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.vivekbalachandra.android.client.Data.Database.DAO.GPSTableDao;
import com.vivekbalachandra.android.client.Data.Database.DAO.TasksTableDao;
import com.vivekbalachandra.android.client.Data.Database.Entity.GPSData;
import com.vivekbalachandra.android.client.Data.Database.Entity.TasksData;

@Database(entities = {TasksData.class,GPSData.class}, version = 1)
public abstract class DatabaseConnector extends RoomDatabase {

    public abstract TasksTableDao tasksTableDao();
    public abstract GPSTableDao gpsTableDao();

    private   static DatabaseConnector databaseConnector=null;

    public static DatabaseConnector getDatabaseInstance(Context context){
        if(databaseConnector==null)
        {
            synchronized (DatabaseConnector.class){
                if(databaseConnector==null){
                    databaseConnector= Room.databaseBuilder(context.getApplicationContext(),DatabaseConnector.class,"tracker_database").build();
                }
            }
        }
        return databaseConnector;
    }



}
