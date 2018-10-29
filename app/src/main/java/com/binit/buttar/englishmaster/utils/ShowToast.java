package com.binit.buttar.englishmaster.utils;

import android.content.Context;
import android.widget.Toast;

public class ShowToast {
    public static void showToast(String toastMessage, Context context){
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }
}
