package com.binit.buttar.englishmaster.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.binit.buttar.englishmaster.R;
import com.binit.buttar.englishmaster.activity.ChapterDetailActivity;
import com.binit.buttar.englishmaster.adapter.Grid_Adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.binit.buttar.englishmaster.utils.CommonPojo;
import com.binit.buttar.englishmaster.utils.CommonThings;
import com.binit.buttar.englishmaster.utils.ConnectionDetector;
import com.binit.buttar.englishmaster.utils.Web_Service;

public class PraticeFragment extends Fragment {
    GridView gridView;
    TextView tv_noResult;
    ArrayList<CommonPojo.Data> videoArrayList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pratice, container, false);
        gridView = view.findViewById(R.id.gridview);
        tv_noResult = view.findViewById(R.id.tv_noResult);
        videoArrayList = new ArrayList<>();
        //Toast.makeText(getContext(), "pratice fragment", Toast.LENGTH_LONG).show();
        getPractice();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChapterDetailActivity.class);
                intent.putExtra("ref", videoArrayList.get(position).getCategory_ref());
                intent.putExtra("detail", "");
                intent.putExtra("from", "pra");

                startActivity(intent);
            }
        });

        return view;
    }


    public void getPractice() {
        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
        connectionDetector.isConnectingToInternet();
        if (!connectionDetector.isConnectingToInternet()) {
            Toast.makeText(getActivity(), "Unable to connect to the server. Please check your internet connection.", Toast.LENGTH_LONG).show();
        } else {
            CommonThings.show_progress_bar(getActivity());
            Call<CommonPojo> call = new Web_Service().getApiService().getpractice("cw0cokck8cok4kwc4w0c8s8cg0sss0kwow840w8g", "JSNlCziWThsbeRm");
            call.enqueue(new Callback<CommonPojo>() {
                @Override
                public void onResponse(Call<CommonPojo> call, Response<CommonPojo> response) {
                    CommonThings.hide_progress_bar();
                    if (response.body().getSuccess().equalsIgnoreCase("true")) {
                        for (int i = 0; i < response.body().getDataArrayList().size(); i++) {
                            CommonPojo.Data videoPojo = new CommonPojo().new Data();
                            videoPojo.setCategory_name(response.body().getDataArrayList().get(i).getCategory_name());
                            videoPojo.setCategory_ref(response.body().getDataArrayList().get(i).getCategory_ref());

                            videoArrayList.add(videoPojo);
                        }

                        if (videoArrayList.size() == 0) {
                            tv_noResult.setVisibility(View.VISIBLE);
                            gridView.setVisibility(View.GONE);
                        } else {
                            tv_noResult.setVisibility(View.GONE);
                            gridView.setVisibility(View.VISIBLE);

                            gridView.setAdapter(new Grid_Adapter(getActivity(), videoArrayList));
                        }
                    } else {
                        tv_noResult.setVisibility(View.VISIBLE);
                        gridView.setVisibility(View.GONE);
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
