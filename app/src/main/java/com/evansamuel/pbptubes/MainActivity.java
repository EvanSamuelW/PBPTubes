package com.evansamuel.pbptubes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static int SPLASH_TIME_OUT = 4000;
    AppPreferencesManager preferencesManager;
    FirebaseAuth fAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
        preferencesManager = new AppPreferencesManager(this);
        if (preferencesManager.getDarkModeState()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user !=null){
                    Intent homeIntentLogin = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(homeIntentLogin);
                }
                else{
                    Intent homeIntent = new Intent(MainActivity.this, ActivityLogin.class);
                    startActivity(homeIntent);
                    finish();
                }

                finish();
            }
        },SPLASH_TIME_OUT);


    }

}