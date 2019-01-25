package com.vivekbalachandra.android.client.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.view.WindowManager;

import com.vivekbalachandra.android.client.Model.UserModel;

public class GenertalUtil {

    public static void check_network_gps(final Context context)
    {
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        final Boolean status = false;
        if( !lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("GPS Service");  // GPS not found
            builder.setMessage("Enable GPS"); // Want to enable?
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            builder.setNegativeButton("No", null);
            builder.create().show();
        }
        /*if( !lm.isProviderEnabled(LocationManager.GPS_PROVIDER) )
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("GPS Service");  // GPS not found
            builder.setMessage("GPS Not enabled"); // Want to enable?
            builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    System.exit(0);
                }
            });
            builder.create().show();
            //check_network_gps(context);
        }*/
    }
    public static UserModel CredentialExists(Context context){

        SharedPreferences sp = context.getApplicationContext().getSharedPreferences("AuthData", Context.MODE_PRIVATE);
        String email = sp.getString("email", null);
        if (email == null)
            return null;
        String token = sp.getString("token", null);

        if (token == null)
            return null;
        UserModel userModel=new UserModel(email);
        userModel.setToken(token);
        return userModel;

    }
}
