package com.binit.buttar.englishmaster.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.binit.buttar.englishmaster.R;
import com.binit.buttar.englishmaster.adapter.List_view_adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.binit.buttar.englishmaster.utils.CommonPojo;
import com.binit.buttar.englishmaster.utils.CommonThings;
import com.binit.buttar.englishmaster.utils.ConnectionDetector;
import com.binit.buttar.englishmaster.utils.Web_Service;

public class SetListActivity extends AppCompatActivity {

    ImageView img_back;
    ListView listView;
    String ref;
    ArrayList<CommonPojo.Data> videoArrayList;
    TextView tv_noResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_list);
        img_back = findViewById(R.id.img_back);
        listView = findViewById(R.id.list);
        tv_noResult = findViewById(R.id.tv_noResult);
        videoArrayList = new ArrayList<>();

        ref = getIntent().getExtras().getString("ref");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        chapterDetails();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SetListActivity.this, ChapterDetailActivity.class);
                intent.putExtra("ref", videoArrayList.get(position).getCategory_ref());
                intent.putExtra("detail", "");
                intent.putExtra("from", "set");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void chapterDetails() {
        ConnectionDetector connectionDetector = new ConnectionDetector(SetListActivity.this);
        connectionDetector.isConnectingToInternet();
        if (!connectionDetector.isConnectingToInternet()) {
            Toast.makeText(SetListActivity.this, "Unable to connect to the server. Please check your internet connection.", Toast.LENGTH_LONG).show();
        } else {
            CommonThings.show_progress_bar(SetListActivity.this);
            Call<CommonPojo> call = new Web_Service().getApiService().getChapterSubcategories("cw0cokck8cok4kwc4w0c8s8cg0sss0kwow840w8g", "JSNlCziWThsbeRm", "http://becomeenglishchampion.com/english/get-primary-subcategories/id/" + ref);
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

                            listView.setAdapter(new List_view_adapter(SetListActivity.this, videoArrayList));
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
}
