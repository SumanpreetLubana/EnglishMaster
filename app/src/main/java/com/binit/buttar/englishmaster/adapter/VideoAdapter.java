package com.binit.buttar.englishmaster.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.binit.buttar.englishmaster.R;

import java.util.ArrayList;

import com.binit.buttar.englishmaster.utils.VideoPojo;

public class VideoAdapter extends BaseAdapter {

    Activity activity;
    LayoutInflater layoutInflater;
    ArrayList<VideoPojo.Data> videoArrayList;

    public VideoAdapter(Activity activity, ArrayList<VideoPojo.Data> videoArrayList) {
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
        View view = layoutInflater.inflate(R.layout.video_adapter, parent, false);
        TextView tv_chap = view.findViewById(R.id.tv_chapter);
        tv_chap.setText(videoArrayList.get(position).getTitle());
        return view;
    }
}
