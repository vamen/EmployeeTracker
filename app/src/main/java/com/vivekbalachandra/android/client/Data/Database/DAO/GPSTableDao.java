package com.vivekbalachandra.android.client.Data.Database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.vivekbalachandra.android.client.Data.Database.Entity.GPSData;

import java.sql.Date;
import java.util.List;

@Dao
public interface GPSTableDao {

    @Insert
    void insert(GPSData data);

    @Query("select * from gps_table where dateTime>:date")
    List<GPSData> getGpsCordinateData(Date date);

    @Query("select * from gps_table")
    List<GPSData> getAllGpsCoordinates();


    @Update
    int updateGpsCoordinateStatus(List<GPSData> instances);

}
