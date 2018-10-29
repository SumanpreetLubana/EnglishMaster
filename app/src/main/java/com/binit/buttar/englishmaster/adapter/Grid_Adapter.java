package com.binit.buttar.englishmaster.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.binit.buttar.englishmaster.R;

import java.util.ArrayList;

import com.binit.buttar.englishmaster.utils.CommonPojo;

public class Grid_Adapter extends BaseAdapter {

    Activity activity;
    LayoutInflater layoutInflater;
    ArrayList<CommonPojo.Data> videoArrayList;


    public Grid_Adapter(Activity activity, ArrayList<CommonPojo.Data> videoArrayList) {
        this.activity = activity;
        this.videoArrayList = videoArrayList;
        layoutInflater = LayoutInflater.from(this.activity);
    }

    @Override
    public int getCount() {
        return videoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.grid_adapter, parent, false);
        TextView tv_chap = view.findViewById(R.id.tv_chapter);
        TextView tv_chap2 = view.findViewById(R.id.tv_chapter2);

        if (videoArrayList.get(position).getCategory_name().contains("-")) {
            String currentString = videoArrayList.get(position).getCategory_name();
            String[] separated = currentString.split("-");

            tv_chap.setText(separated[0]);
            tv_chap2.setText(separated[1]);
        }else {
            tv_chap.setText(videoArrayList.get(position).getCategory_name());

        }
        return view;
    }
}
