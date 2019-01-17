package com.vivekbalachandra.android.client.Data.Database.Entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import com.vivekbalachandra.android.client.Data.Database.DateConverter;

import java.util.Date;

@Entity(tableName = "tasks_table")
public class TasksData {

    @PrimaryKey
    public int id;

    @NonNull

    public Date date;

    @NonNull
    public String header;

    @NonNull
    public String lat,lang;

    public String address;
    public String discription;
    public String status;

    public void TaskTable(){

    }

}
