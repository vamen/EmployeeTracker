package com.vivekbalachandra.android.client.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.vivekbalachandra.android.client.Model.UserModel;

public class GenertalUtil {
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
