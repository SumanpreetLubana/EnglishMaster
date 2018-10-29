package com.binit.buttar.englishmaster.utils;

import android.app.Activity;
import android.app.ProgressDialog;

public class CommonThings {

    public static ProgressDialog progressBar;

    public static void show_progress_bar(Activity activity) {
        progressBar = new ProgressDialog(activity);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Loading...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();//displays the progress bar
    }

    public static void hide_progress_bar() {
        progressBar.dismiss();
    }
}
