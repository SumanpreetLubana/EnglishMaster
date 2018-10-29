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
        vi = getIntent().getExtras().getString("video");
        img_back = findViewById(R.id.img_back);
        videoView = findViewById(R.id.videoView);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Uri uri = Uri.parse(vi);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
