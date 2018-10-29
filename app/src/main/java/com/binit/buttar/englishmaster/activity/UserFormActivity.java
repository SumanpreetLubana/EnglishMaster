package com.binit.buttar.englishmaster.activity;

import android.app.Activity;

import instamojo.library.InstapayListener;
import instamojo.library.InstamojoPay;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.binit.buttar.englishmaster.R;
import com.binit.buttar.englishmaster.utils.Logger;
import com.binit.buttar.englishmaster.utils.SessionManager;

public class UserFormActivity extends AppCompatActivity {

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);
        // Call the function callInstamojo to start payment here


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here


        return super.onOptionsItemSelected(item);
    }


}
