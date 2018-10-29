
package com.binit.buttar.englishmaster.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.binit.buttar.englishmaster.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.JsonElement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.binit.buttar.englishmaster.utils.CommonThings;
import com.binit.buttar.englishmaster.utils.ConnectionDetector;
import com.binit.buttar.englishmaster.utils.Web_Service;

public class ChapterDetailActivity extends AppCompatActivity {

    ImageView img_back;
    String ref, detail, from;
    WebView webView;
    private AdView mAdView;
    //String deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_detail);

    }


}
