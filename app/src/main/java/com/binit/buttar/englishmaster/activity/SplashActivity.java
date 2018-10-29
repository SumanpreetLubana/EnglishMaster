package com.binit.buttar.englishmaster.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.binit.buttar.englishmaster.R;

public class SplashActivity extends AppCompatActivity {

    Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myintent = new Intent(this, DashboardActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(myintent);
                finish();
            }
        }, 1000);
    }
}


