package com.vivekbalachandra.android.client.Network;

import com.vivekbalachandra.android.client.Model.TaskModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TrackerApis {

@POST("login")
Call<String> login(@Field("username") String username,@Field("username") String password);

@POST("task")
Call<List<TaskModel>> getTasks(@Header("Authorization") String token,@Field("username") String username);



}
