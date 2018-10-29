package com.binit.buttar.englishmaster.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.binit.buttar.englishmaster.R;
import com.binit.buttar.englishmaster.adapter.VideoAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.binit.buttar.englishmaster.utils.CommonThings;
import com.binit.buttar.englishmaster.utils.ConnectionDetector;
import com.binit.buttar.englishmaster.utils.VideoPojo;
import com.binit.buttar.englishmaster.utils.Web_Service;


public class VideosFragment extends Fragment {

    ListView listView;
    ArrayList<VideoPojo.Data> videoArrayList;
    TextView tv_noResult;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        listView = view.findViewById(R.id.listview);
        tv_noResult = view.findViewById(R.id.tv_noResult);
        videoArrayList = new ArrayList<>();
        //Toast.makeText(getContext(), "video fragment", Toast.LENGTH_LONG).show();
        getVideo();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* Intent intent = new Intent(getActivity(), VideoViewActivity.class);
                intent.putExtra("video", videoArrayList.get(position).getUrl());
                startActivity(intent);*/
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(videoArrayList.get(position).getUrl())));

            }
        });
        return view;
    }


    public void getVideo() {
        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
        connectionDetector.isConnectingToInternet();
        if (!connectionDetector.isConnectingToInternet()) {
            Toast.makeText(getActivity(), "Unable to connect to the server. Please check your internet connection.", Toast.LENGTH_LONG).show();
        } else {
            CommonThings.show_progress_bar(getActivity());
            Call<VideoPojo> call = new Web_Service().getApiService().getvideos("cw0cokck8cok4kwc4w0c8s8cg0sss0kwow840w8g", "JSNlCziWThsbeRm");
            call.enqueue(new Callback<VideoPojo>() {
                @Override
                public void onResponse(Call<VideoPojo> call, Response<VideoPojo> response) {
                    CommonThings.hide_progress_bar();
                    if (response.body().getSuccess().equalsIgnoreCase("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            VideoPojo.Data videoPojo = new VideoPojo().new Data();
                            videoPojo.setId(response.body().getData().get(i).getId());
                            videoPojo.setName(response.body().getData().get(i).getName());
                            videoPojo.setTitle(response.body().getData().get(i).getTitle());
                            videoPojo.setVideo_ref(response.body().getData().get(i).getVideo_ref());
                            videoPojo.setUrl(response.body().getData().get(i).getUrl());

                            videoArrayList.add(videoPojo);
                        }

                        if (videoArrayList.size() == 0) {
                            tv_noResult.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                        } else {
                            tv_noResult.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);

                            listView.setAdapter(new VideoAdapter(getActivity(), videoArrayList));
                        }
                    } else {
                        tv_noResult.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<VideoPojo> call, Throwable t) {
                    CommonThings.hide_progress_bar();
                }
            });

        }
    }


}
