package com.vivekbalachandra.android.client.Model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vivekbalachandra.android.client.Data.DataBridge;
import com.vivekbalachandra.android.client.Data.Database.Entity.TasksData;

import java.util.List;
import java.util.Observer;

public class TasksViewModel extends AndroidViewModel {

    private DataBridge dataBridge=null;

    private LiveData<List<TasksData>> tasks;
    public TasksViewModel(@NonNull Application application) {
        super(application);
        dataBridge=new DataBridge(application);
        tasks=dataBridge.getTodaysTask();
    }

    public LiveData<List<TasksData>> getAllTasks(){
        return tasks;
    }

}
