package com.binit.buttar.englishmaster.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.binit.buttar.englishmaster.R;


public class BuyBookFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Toast.makeText(getContext(), "buy book fragment", Toast.LENGTH_LONG).show();
        return inflater.inflate(R.layout.fragment_buy_book, container, false);
    }

}