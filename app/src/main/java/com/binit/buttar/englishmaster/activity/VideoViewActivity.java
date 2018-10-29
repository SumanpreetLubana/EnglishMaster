package com.binit.buttar.englishmaster.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.binit.buttar.englishmaster.R;

public class VideoViewActivity extends AppCompatActivity {

    ImageView img_back;
    VideoView videoView;
    String vi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
