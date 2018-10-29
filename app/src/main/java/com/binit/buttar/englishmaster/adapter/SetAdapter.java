package com.binit.buttar.englishmaster.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.binit.buttar.englishmaster.R;

public class SetAdapter  extends BaseAdapter {

    Activity activity;
    LayoutInflater layoutInflater;

    public SetAdapter(Activity activity) {
        this.activity = activity;
        layoutInflater = LayoutInflater.from(this.activity);
    }

    @Override
    public int getCount() {
        return 10;
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
        tv_chap.setText("Set " + position);
        return view;
    }
}
