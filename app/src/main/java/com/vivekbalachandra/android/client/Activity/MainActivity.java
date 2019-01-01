package com.vivekbalachandra.android.client.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vivekbalachandra.android.client.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mTaskList=null;
    private RecyclerView.LayoutManager mLayoutManager=null;

    public static boolean isLoggedIn(){
        return true;
    }

    public static boolean isTrackerServiceRunning(){
        return true;
    }
    public  void bindViews() {

        mTaskList=findViewById(R.id.task_list);
        mLayoutManager=new LinearLayoutManager(this);
        mTaskList.setLayoutManager(mLayoutManager);

    }

    public void startTrackerService(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isLoggedIn()) {

            bindViews();
            if(isTrackerServiceRunning()){
                startTrackerService();
            }
        }
    }


}
