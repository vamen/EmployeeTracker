package com.vivekbalachandra.android.client.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroatcastRecievers extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
            Log.e("mGpsSwitchStateReceiver","mGpsSwitchStateReceiver");
            // Make an action or refresh an already managed state
        }
        Log.e("Intents", intent.getAction());
    }
    public BroatcastRecievers(){

    }
}
