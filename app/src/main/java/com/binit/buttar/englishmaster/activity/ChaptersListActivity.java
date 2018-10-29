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

        img_back = findViewById(R.id.img_back);
        listView = findViewById(R.id.list);
        tv_noResult = findViewById(R.id.tv_noResult);
        videoArrayList = new ArrayList<>();

        primary = getIntent().getExtras().getString("primary");
        cat = getIntent().getExtras().getString("cat");

        chapterDetails();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (videoArrayList.get(position).getChecked().equals("1")){
                    Toast.makeText(ChaptersListActivity.this,"This chapter is paid",Toast.LENGTH_LONG).show();
                }else {
                    if (primary.equals("1")) {
                        Intent intent = new Intent(ChaptersListActivity.this, SetListActivity.class);
                        intent.putExtra("ref", videoArrayList.get(position).getCategory_ref());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(ChaptersListActivity.this, ChapterDetailActivity.class);
                        intent.putExtra("ref", "");
                        intent.putExtra("from", "set");
                        intent.putExtra("detail", videoArrayList.get(position).getDescription());
                        startActivity(intent);
                    }
                }
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                // Check the LogCat to get your test device ID
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //addTestDevice(deviceId)
                .build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void chapterDetails() {
        ConnectionDetector connectionDetector = new ConnectionDetector(ChaptersListActivity.this);
        connectionDetector.isConnectingToInternet();
        if (!connectionDetector.isConnectingToInternet()) {
            Toast.makeText(ChaptersListActivity.this, "Unable to connect to the server. Please check your internet connection.", Toast.LENGTH_LONG).show();
        } else {
            CommonThings.show_progress_bar(ChaptersListActivity.this);
            Call<CommonPojo> call = new Web_Service().getApiService().getChapterSubcategories("cw0cokck8cok4kwc4w0c8s8cg0sss0kwow840w8g", "JSNlCziWThsbeRm", "http://becomeenglishchampion.com/english/get-chapter-cubcategories/id/" + cat);
            call.enqueue(new Callback<CommonPojo>() {
                @Override
                public void onResponse(Call<CommonPojo> call, Response<CommonPojo> response) {
                    CommonThings.hide_progress_bar();
                    if (response.body().getSuccess().equalsIgnoreCase("true")) {
                        for (int i = 0; i < response.body().getDataArrayList().size(); i++) {
                            CommonPojo.Data videoPojo = new CommonPojo().new Data();
                            videoPojo.setDescription(response.body().getDataArrayList().get(i).getDescription());
                            videoPojo.setCategory_ref(response.body().getDataArrayList().get(i).getCategory_ref());
                            videoPojo.setCategory_name(response.body().getDataArrayList().get(i).getCategory_name());
                            videoPojo.setChecked(response.body().getDataArrayList().get(i).getChecked());
                            videoArrayList.add(videoPojo);
                        }

                        if (videoArrayList.size() == 0) {
                            tv_noResult.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                        } else {
                            tv_noResult.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);

                            listView.setAdapter(new List_view_adapter(ChaptersListActivity.this, videoArrayList));
                        }
                    } else {
                        tv_noResult.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<CommonPojo> call, Throwable t) {
                    CommonThings.hide_progress_bar();
                }
            });
        }
    }


    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
