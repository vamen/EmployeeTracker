package com.vivekbalachandra.android.client.Data.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.vivekbalachandra.android.client.Data.Database.DateConverter;

import java.util.Date;

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
