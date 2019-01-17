package com.vivekbalachandra.android.client.Data.Database.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.vivekbalachandra.android.client.Data.Database.DateConverter;
import com.vivekbalachandra.android.client.Data.Database.Entity.TasksData;

import java.util.Date;
import java.util.List;
import java.util.List;

@Dao
public interface TasksTableDao {

    @Insert
    void insert_task(TasksData instance);

    @Query("Select * from tasks_table where date = :date")
    LiveData<List<TasksData>> getTodaysTask( Date date);

    @Delete
    void delete_task(List<TasksData> tasksData);

    @Query("Select * from tasks_table where date < :date")
    LiveData<List<TasksData>> getPreviousTask(Date date);



}
