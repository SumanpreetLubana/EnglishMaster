package com.binit.buttar.englishmaster.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.binit.buttar.englishmaster.R;
import com.binit.buttar.englishmaster.activity.ChaptersListActivity;
import com.binit.buttar.englishmaster.activity.UserFormActivity;
import com.binit.buttar.englishmaster.adapter.Grid_Adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.binit.buttar.englishmaster.utils.CommonPojo;
import com.binit.buttar.englishmaster.utils.ConnectionDetector;
import com.binit.buttar.englishmaster.utils.Logger;
import com.binit.buttar.englishmaster.utils.SessionManager;
import com.binit.buttar.englishmaster.utils.Web_Service;


public class HomeFragment extends Fragment {
    SessionManager sessionManager;
    boolean isAdFreeuser;

    GridView gridView;
    ArrayList<CommonPojo.Data> videoArrayList;
    TextView tv_noResult;
    ProgressDialog progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = view.findViewById(R.id.gridview);
        tv_noResult = view.findViewById(R.id.tv_noResult);
        videoArrayList = new ArrayList<>();
        sessionManager = new SessionManager(getActivity());
        isAdFreeuser = sessionManager.getUserDetails();
        getChapter();

        //Toast.makeText(getContext(), "home fragment", Toast.LENGTH_LONG).show();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Logger.showMessage("position : " + position);
                if (position >= 6) {

                    if (!isAdFreeuser)
                        showPaidDialog();
                    else {
                        Intent intent = new Intent(getActivity(), ChaptersListActivity.class);
                        intent.putExtra("primary", videoArrayList.get(position).getPrimary());
                        intent.putExtra("cat", videoArrayList.get(position).getCategory_ref());
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(getActivity(), ChaptersListActivity.class);
                    intent.putExtra("primary", videoArrayList.get(position).getPrimary());
                    intent.putExtra("cat", videoArrayList.get(position).getCategory_ref());
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    private void showPaidDialog() {
        if (getActivity() != null)
            if (!getActivity().isFinishing()) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("This is paid chapter");
                alertDialog.setMessage("To continue learning english you have to pay a small amount");
                alertDialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent userForm = new Intent(getActivity(), UserFormActivity.class);
                        startActivity(userForm);
                    }
                });
                alertDialog.setNegativeButton("Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setCancelable(false);
                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.setCanceledOnTouchOutside(false);
                alertDialog1.show();
            }
    }

    public void getChapter() {
        progressBar = new ProgressDialog(getActivity());
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
        connectionDetector.isConnectingToInternet();
        if (!connectionDetector.isConnectingToInternet()) {
            Toast.makeText(getActivity(), "Unable to connect to the server. Please check your internet connection.", Toast.LENGTH_LONG).show();
        } else {
            progressBar.show();
            Call<CommonPojo> call = new Web_Service().getApiService().getchapter("cw0cokck8cok4kwc4w0c8s8cg0sss0kwow840w8g", "JSNlCziWThsbeRm");
            call.enqueue(new Callback<CommonPojo>() {
                @Override
                public void onResponse(Call<CommonPojo> call, Response<CommonPojo> response) {

                    if (response.body().getSuccess().equalsIgnoreCase("true")) {
                        progressBar.dismiss();
                        for (int i = 0; i < response.body().getDataArrayList().size(); i++) {
                            CommonPojo.Data videoPojo = new CommonPojo().new Data();
                            videoPojo.setCategory_name(response.body().getDataArrayList().get(i).getCategory_name());
                            videoPojo.setCategory_ref(response.body().getDataArrayList().get(i).getCategory_ref());
                            videoPojo.setId(response.body().getDataArrayList().get(i).getId());
                            videoPojo.setPrimary(response.body().getDataArrayList().get(i).getPrimary());
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
                        progressBar.dismiss();
                        tv_noResult.setVisibility(View.GONE);
                        gridView.setVisibility(View.VISIBLE);
                    }
                }


                @Override
                public void onFailure(Call<CommonPojo> call, Throwable t) {
                    progressBar.dismiss();
                }
            });

        }
    }


}