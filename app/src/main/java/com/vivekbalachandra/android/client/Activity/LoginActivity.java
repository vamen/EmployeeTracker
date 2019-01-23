package com.vivekbalachandra.android.client.Activity;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.vivekbalachandra.android.client.Network.ApiClient;
import com.vivekbalachandra.android.client.Network.TrackerApis;
import com.vivekbalachandra.android.client.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {
    private static String TAG=LoginActivity.class.getSimpleName();
    private static  TrackerApis trackerApis;
    private AutoCompleteTextView emailView;
    private EditText pass;
    private Button click;
    static {
        trackerApis= ApiClient.getClient().create(TrackerApis.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailView = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        click = findViewById(R.id.email_sign_in_button);
        click.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"looging in");
                Call<String> login = trackerApis.login(emailView.getText().toString(), pass.getText().toString());
                login.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.body() != null) {
                            PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("csrf_token", response.body()).apply();
                            PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("username", emailView.getText().toString()).apply();
//                            Log.e(TAG, PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).getString("csrf_token", "defaultStringIfNothingFound"));
                              finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }
                });


            }
        });
    }

}

