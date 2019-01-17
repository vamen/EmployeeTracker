package com.vivekbalachandra.android.client.Data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.Looper;

import com.vivekbalachandra.android.client.Data.Database.DAO.GPSTableDao;
import com.vivekbalachandra.android.client.Data.Database.DAO.TasksTableDao;
import com.vivekbalachandra.android.client.Data.Database.DatabaseConnector;
import com.vivekbalachandra.android.client.Data.Database.Entity.GPSData;
import com.vivekbalachandra.android.client.Data.Database.Entity.TasksData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataBridge {

    private DatabaseConnector databaseConnector;
    private TasksTableDao tasksTableDao;
    private GPSTableDao gpsTableDao;

    public  DataBridge(Application application){
        databaseConnector=DatabaseConnector.getDatabaseInstance(application);
        tasksTableDao=databaseConnector.tasksTableDao();
        gpsTableDao=databaseConnector.gpsTableDao();
    }

    LiveData<List<TasksData>> getTodaysTask(){
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        return tasksTableDao.getTodaysTask(date);
    }

    void insertGpsData(GPSData gpsData){

        if(Looper.myLooper()==Looper.getMainLooper())
        {
         // Todo:run code to execute query in background thread
        }else{

            gpsTableDao.insert(gpsData);
        }
    }


}
