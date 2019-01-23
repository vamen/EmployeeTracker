package com.vivekbalachandra.android.client.Data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.vivekbalachandra.android.client.Data.Database.DAO.GPSTableDao;
import com.vivekbalachandra.android.client.Data.Database.DAO.TasksTableDao;
import com.vivekbalachandra.android.client.Data.Database.DatabaseConnector;
import com.vivekbalachandra.android.client.Data.Database.Entity.GPSData;
import com.vivekbalachandra.android.client.Data.Database.Entity.TasksData;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DataBridge {

    private DatabaseConnector databaseConnector;
    private TasksTableDao tasksTableDao;
    private GPSTableDao gpsTableDao;
    public Application application;
    private  ExecutorService mExecutorService=null;

    public  DataBridge(Application application){
        this.application=application;

        databaseConnector=DatabaseConnector.getDatabaseInstance(application);
        tasksTableDao=databaseConnector.tasksTableDao();
        gpsTableDao=databaseConnector.gpsTableDao();
        mExecutorService=null;
    }

    public LiveData<List<TasksData>> getTodaysTask(){

        java.sql.Date date = new java.sql.Date(new Date().getTime());
        return tasksTableDao.getTodaysTask(date);
    }

    public void insertGpsData(final GPSData gpsData){

        if(Looper.myLooper()==Looper.getMainLooper())
        {

         // TODO:run code to execute query in background thread
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    gpsTableDao.insert(gpsData);
                }
            };
            if(mExecutorService==null)
            {
                mExecutorService=Executors.newCachedThreadPool();
            }
            mExecutorService.execute(runnable);
        }else{

            gpsTableDao.insert(gpsData);
        }

    }

    public List<GPSData> getGpsData(){

        if(Looper.myLooper()==Looper.getMainLooper())
        {

            // TODO:run code to execute query in background thread
            Future<List<GPSData>> future = null;

            if(mExecutorService==null)
            {
                mExecutorService=Executors.newCachedThreadPool();
            }
            future = mExecutorService.submit(new Callable<List<GPSData>>() {
                @Override
                public List<GPSData> call() throws Exception {
                    java.sql.Date date = new java.sql.Date(new Date().getTime());
                    List<GPSData> gpsDatas= gpsTableDao.getGpsCordinateData(date);
                    return gpsDatas;
                }
            });
            try{
                return  future.get();
            }
            catch (Exception e){
                Log.e("Threading Exception",e.toString());
                return null;
            }
        }else{

            java.sql.Date date = new java.sql.Date(new Date().getTime());
            List<GPSData> gpsDatas = gpsTableDao.getGpsCordinateData(date);
            return gpsDatas;
        }


    }

    public void updateGpsData(final List<GPSData> gpsDatas){
        for(GPSData gpsData:gpsDatas){
            gpsData.status=1;
        }
        if(Looper.myLooper()==Looper.getMainLooper()){

            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    gpsTableDao.updateGpsCoordinateStatus(gpsDatas);
                }
            };
            if(mExecutorService==null)
            {
                mExecutorService=Executors.newCachedThreadPool();
            }
            mExecutorService.execute(runnable);
        }else {

            gpsTableDao.updateGpsCoordinateStatus(gpsDatas);

        }

    }

    void insertTaskData(final TasksData tasksData){
        if(Looper.myLooper()==Looper.getMainLooper()){

            Runnable runnable=new Runnable(){
                @Override
                public  void run(){

                    tasksTableDao.insert_task(tasksData);
            }
            };

            if(mExecutorService==null){
                mExecutorService=Executors.newCachedThreadPool();
            }
        }
        else{
            tasksTableDao.insert_task(tasksData);
        }
    }

    void insertMultipleTaskData(final List<TasksData> tasksData){
        if(Looper.myLooper()==Looper.getMainLooper()){

            Runnable runnable=new Runnable(){
                @Override
                public  void run(){

                    tasksTableDao.insertMultipleTask(tasksData);
                }
            };
            if(mExecutorService==null)
            {
                mExecutorService=Executors.newCachedThreadPool();
            }
            mExecutorService.execute(runnable);
        }
        else {

            tasksTableDao.insertMultipleTask(tasksData);
        }
    }


    // must be called when app is getting shutting down
    void shutDownExecutor(){
        mExecutorService.shutdown();
    }



}
