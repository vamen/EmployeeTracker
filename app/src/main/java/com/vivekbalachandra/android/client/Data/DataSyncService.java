package com.vivekbalachandra.android.client.Data;

import android.app.IntentService;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.util.Log;

import com.vivekbalachandra.android.client.Data.Database.Entity.GPSData;
import com.vivekbalachandra.android.client.Model.UserModel;
import com.vivekbalachandra.android.client.Network.ApiClient;
import com.vivekbalachandra.android.client.Network.TrackerApis;
import com.vivekbalachandra.android.client.Util.GenertalUtil;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DataSyncService extends IntentService {

    public static final String ACTION_SYNC = "sync_service";
    public static String TAG = DataSyncService.class.getSimpleName();
    DataBridge database = null;
    TrackerApis apiClient = null;
    private static UserModel userModel = null;

    public DataSyncService() {
        super("DataSyncService");
        database = new DataBridge(getApplication());
        apiClient = ApiClient.getClient().create(TrackerApis.class);


    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SYNC.equals(action)) {
                userModel = GenertalUtil.CredentialExists(getApplicationContext());
                if (userModel != null)
                    mSyncData();

            }
        }
    }


    private void mSyncData() {

        try {


//            push codes to another class
            database.insertMultipleTaskData(apiClient.getTasks(userModel.getToken(), userModel.getUser()).execute().body());

            List<GPSData> gpsDatas=database.getGpsData();
            Response<Integer> response=apiClient.sendGPS(userModel.getToken(),gpsDatas).execute();
            if(response.isSuccessful()){
               if(gpsDatas.size()==response.body())
               {
                    database.updateGpsData(gpsDatas);
               }
            }
        } catch (IOException e) {
            // TODO:Handle errors
            Log.d(TAG, "network error");
        }
    }


}
