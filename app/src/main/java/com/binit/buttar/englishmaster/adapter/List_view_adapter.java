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

public class List_view_adapter extends BaseAdapter {

    Activity activity;
    LayoutInflater layoutInflater;
    ArrayList<CommonPojo.Data> videoArrayList;

    public List_view_adapter(Activity activity, ArrayList<CommonPojo.Data> videoArrayList) {
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
        View view = layoutInflater.inflate(R.layout.list_view_adapter, parent, false);
        TextView tv_chap = view.findViewById(R.id.tv_chapter);
        tv_chap.setText(videoArrayList.get(position).getCategory_name());
        return view;
    }
}
