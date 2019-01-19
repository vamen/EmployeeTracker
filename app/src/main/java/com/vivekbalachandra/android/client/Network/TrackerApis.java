package com.vivekbalachandra.android.client.Network;

import com.vivekbalachandra.android.client.Data.Database.Entity.TasksData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TrackerApis {

@FormUrlEncoded
@POST("login")
Call<String> login(@Field("username") String username,@Field("username") String password);

@POST("task")
Call<List<TasksData>> getTasks(@Header("Authorization") String token, @Field("username") String username);




}
