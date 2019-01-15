package com.vivekbalachandra.android.client.Data.Database.DAO;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.vivekbalachandra.android.client.Data.Database.Entity.GPSData;

import java.sql.Date;
import java.util.List;

public interface GPSTableDao {

    @Insert
    void insert(GPSData data);

    @Query("select * from GPSData where dateTime=:date")
    List<GPSData> getGpsCordinateData(Date date);

    @Update
    List<GPSData> updateGpsCordinateStatus(List<GPSData> instances);
}
