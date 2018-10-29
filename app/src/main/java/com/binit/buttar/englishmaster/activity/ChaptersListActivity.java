package com.binit.buttar.englishmaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.binit.buttar.englishmaster.R;
import com.binit.buttar.englishmaster.adapter.List_view_adapter;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.binit.buttar.englishmaster.utils.CommonPojo;
import com.binit.buttar.englishmaster.utils.CommonThings;
import com.binit.buttar.englishmaster.utils.ConnectionDetector;
import com.binit.buttar.englishmaster.utils.Web_Service;

public class ChaptersListActivity extends AppCompatActivity {

    ImageView img_back;
    ListView listView;
    String cat, primary;
    TextView tv_noResult;
    ArrayList<CommonPojo.Data> videoArrayList;
    private AdView mAdView;
    //String deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters_list);

        //String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        //deviceId = md5(android_id).toUpperCase();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



}
