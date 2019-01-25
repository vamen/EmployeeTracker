package com.vivekbalachandra.android.client.Activity;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vivekbalachandra.android.client.Adapters.TaskListAdapter;
import com.vivekbalachandra.android.client.Data.DataSyncService;
import com.vivekbalachandra.android.client.Data.Database.Entity.TasksData;
import com.vivekbalachandra.android.client.Model.TaskModel;
import com.vivekbalachandra.android.client.Model.TasksViewModel;
import com.vivekbalachandra.android.client.Model.UserModel;
import com.vivekbalachandra.android.client.Network.ApiClient;
import com.vivekbalachandra.android.client.Network.TrackerApis;
import com.vivekbalachandra.android.client.R;
import com.vivekbalachandra.android.client.Services.Location_Service;
import com.vivekbalachandra.android.client.Util.BroatcastRecievers;
import com.vivekbalachandra.android.client.Util.GenertalUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

// TODO : Replace constant strings by constant variables
public class MainActivity extends AppCompatActivity {

    private BroatcastRecievers recv= null;
    private RecyclerView mTaskList = null;
    private RecyclerView.LayoutManager mLayoutManager = null;
    private static UserModel userModel=null;
    private TasksViewModel tasksViewModel=null;
    private static  TrackerApis trackerApis=null;
    private TaskListAdapter adapter=null;

    static {
        trackerApis=ApiClient.getClient().create(TrackerApis.class);
    }


    public void bindViews() {
        mTaskList = findViewById(R.id.task_list);
        mLayoutManager = new LinearLayoutManager(this);
        mTaskList.setLayoutManager(mLayoutManager);

    }




    protected void initialize_data(){
        tasksViewModel=ViewModelProviders.of(this).get(TasksViewModel.class);
        if(adapter==null)
        adapter=new TaskListAdapter(this.getApplicationContext(),tasksViewModel.getAllTasks().getValue());
        tasksViewModel.getAllTasks().observe(this, new Observer<List<TasksData>>() {
            @Override
            public void onChanged(@Nullable List<TasksData> tasksData) {
                adapter.setDataset(tasksData);
            }
        });
    }

    public boolean isTrackerServiceRunning() {
        // TODO:Impliment this function
        return false;
    }



    public void startTrackerService() {
        // TODO:Impliment this function
    }

    public void startDataSyncService(){
        startService(new Intent(this,DataSyncService.class));
    }
    public void start_GPS_service()
    {
        Intent intent = new Intent(MainActivity.this, Location_Service.class);
        startService(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recv = new BroatcastRecievers();
        setContentView(R.layout.activity_main);
        GenertalUtil.check_network_gps(this);
        userModel=GenertalUtil.CredentialExists(this.getApplicationContext());

        if (userModel!=null) {

            if (isTrackerServiceRunning()) {
                startTrackerService();
            }

            startDataSyncService();
            bindViews();
            initialize_data();
        }else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        registerReceiver(mGpsSwitchStateReceiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));
        registerReceiver(mNetworkSwitchStateReciver, new IntentFilter("com.devglan.broadcastreceiver.NETWORK_SWITCH_FILTER"));
        //registerReceiver(mWifiSwitchStateReciver, new IntentFilter(LocationManager.));
    }
    private  void func()
    {
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }
    private BroadcastReceiver mGpsSwitchStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
                Log.e("mGpsSwitchStateReceiver","mGpsSwitchStateReceiver");
                // Make an action or refresh an already managed state
            }
        }
    };
    public static boolean internt_check(Context context) {
        boolean result = false;
        if (context != null) {
            final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                if (networkInfo != null) {
                    result = networkInfo.isConnected();
                }
            }
        }
        return result;
    }
    private BroadcastReceiver mNetworkSwitchStateReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().matches("com.devglan.broadcastreceiver.NETWORK_SWITCH_FILTER")) {
                Log.e("mNetworkSwitchStateRecr","mNetworkSwitchStateReciver");
                // Make an action or refresh an already managed state
            }
        }
    };
    private BroadcastReceiver mWifiSwitchStateReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().matches("android.location.NETWORK_PROVIDER")) {
                Log.e("mGpsSwitchStateReceiver","mGpsSwitchStateReceiver");
                // Make an action or refresh an already managed state
            }
        }
    };
}
