package com.vivekbalachandra.android.client.Data.Database.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vivekbalachandra.android.client.Data.Database.Entity.TasksData;

import java.sql.Date;
import java.util.List;

@Dao
public interface TasksTableDao {

    @Insert
    void insert_task(TasksData instance);

    @Query("Select * from TasksData where date = :date")
    LiveData<List<TasksData>> getTodaysTask(Date date);

    @Delete
    void delete_task(List<TasksData> tasksData);

    @Query("Select * from TasksData where date < :date")
    LiveData<List<TasksData>> getPreviousTask(Date date);



}
