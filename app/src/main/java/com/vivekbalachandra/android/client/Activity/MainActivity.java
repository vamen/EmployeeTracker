package com.vivekbalachandra.android.client.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vivekbalachandra.android.client.Model.TaskModel;
import com.vivekbalachandra.android.client.Model.UserModel;
import com.vivekbalachandra.android.client.Network.ApiClient;
import com.vivekbalachandra.android.client.Network.TrackerApis;
import com.vivekbalachandra.android.client.R;
import com.vivekbalachandra.android.client.Util.GenertalUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

// TODO : Replace constant strings by constant variables
public class MainActivity extends AppCompatActivity {

    private RecyclerView mTaskList = null;
    private RecyclerView.LayoutManager mLayoutManager = null;
    private static UserModel userModel=null;

    private static  TrackerApis trackerApis=null;

    static {
        trackerApis=ApiClient.getClient().create(TrackerApis.class);
    }

    public boolean isTrackerServiceRunning() {
        return true;
    }

    public void bindViews() {

        mTaskList = findViewById(R.id.task_list);
        mLayoutManager = new LinearLayoutManager(this);
        mTaskList.setLayoutManager(mLayoutManager);

    }

    public void startTrackerService() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userModel=GenertalUtil.CredentialExists(this.getApplicationContext());
        if (userModel!=null) {
            bindViews();
            Call<List<TaskModel>> data=trackerApis.getTasks(userModel.getToken(),userModel.getUser());
            if (isTrackerServiceRunning()) {
                startTrackerService();
            }
        }else{
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
        }
    }


}
