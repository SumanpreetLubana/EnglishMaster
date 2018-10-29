
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
        img_back = findViewById(R.id.img_back);
        webView = findViewById(R.id.web);


        ref = getIntent().getExtras().getString("ref");
        detail = getIntent().getExtras().getString("detail");
        from = getIntent().getExtras().getString("from");

        if (ref.equals("")) {
            String htmlAsString = detail;
            webView.loadData(htmlAsString, "text/html", "UTF-8");
        } else {
            if (from.equals("set")) {
                getSetDetail();
            } else {
                chapterDetails();
            }
        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                // Check the LogCat to get your test device ID
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // .addTestDevice(deviceId)
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


    public void getSetDetail() {
        ConnectionDetector connectionDetector = new ConnectionDetector(ChapterDetailActivity.this);
        connectionDetector.isConnectingToInternet();
        if (!connectionDetector.isConnectingToInternet()) {
            Toast.makeText(ChapterDetailActivity.this, "Unable to connect to the server. Please check your internet connection.", Toast.LENGTH_LONG).show();
        } else {
            CommonThings.show_progress_bar(ChapterDetailActivity.this);
            Call<JsonElement> call = new Web_Service().getApiService().getChapterSubcategoryDetail("cw0cokck8cok4kwc4w0c8s8cg0sss0kwow840w8g", "JSNlCziWThsbeRm", "http://becomeenglishchampion.com/english/getChapter-subcategory-detail/id/" + ref);
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    CommonThings.hide_progress_bar();
                    if (response.body().getAsJsonObject().get("success").getAsString().equalsIgnoreCase("true")) {
                        if (response.body().getAsJsonObject().get("data").getAsJsonObject().get("checked").getAsString().equalsIgnoreCase("1")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ChapterDetailActivity.this);
                            builder.setMessage("This chapter is paid")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // FIRE ZE MISSILES!
                                            dialog.dismiss();
                                            onBackPressed();
                                        }
                                    });
                            // Create the AlertDialog object and return it
                            builder.create();
                            builder.show();
                        } else {
                            String htmlAsString = response.body().getAsJsonObject().get("data").getAsJsonObject().get("description").getAsString();
                            webView.loadData(htmlAsString, "text/html", "UTF-8");
                        }
                    } else {
                        Toast.makeText(ChapterDetailActivity.this, "Oop's something went wrong", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    CommonThings.hide_progress_bar();
                }
            });

        }
    }

    public void chapterDetails() {
        ConnectionDetector connectionDetector = new ConnectionDetector(ChapterDetailActivity.this);
        connectionDetector.isConnectingToInternet();
        if (!connectionDetector.isConnectingToInternet()) {
            Toast.makeText(ChapterDetailActivity.this, "Unable to connect to the server. Please check your internet connection.", Toast.LENGTH_LONG).show();
        } else {
            CommonThings.show_progress_bar(ChapterDetailActivity.this);
            Call<JsonElement> call = new Web_Service().getApiService().getpracticedetail("cw0cokck8cok4kwc4w0c8s8cg0sss0kwow840w8g", "JSNlCziWThsbeRm", "http://becomeenglishchampion.com/english/get-practice-detail/id/" + ref);
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    CommonThings.hide_progress_bar();
                    if (response.body().getAsJsonObject().get("success").getAsString().equalsIgnoreCase("true")) {
                        String htmlAsString = response.body().getAsJsonObject().get("data").getAsJsonObject().get("description").getAsString();
                        webView.loadData(htmlAsString, "text/html", "UTF-8");

                    } else {
                        //Toast.makeText(ChapterDetailActivity.this, "Oop's something went wrong", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
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
