package com.vivekbalachandra.android.client.Data.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Date;

@Entity(tableName = "gps_table")
public class GPSData {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public Date dateTime;
    public String lat;
    public String lang;
    public int status;
}
