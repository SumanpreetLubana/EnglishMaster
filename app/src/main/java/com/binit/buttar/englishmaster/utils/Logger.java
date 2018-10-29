package com.binit.buttar.englishmaster.utils;

import android.util.Log;

public class Logger {

    public static boolean IS_TESTING_MODE= true;

    public static void showMessage(String message){
        if (IS_TESTING_MODE){
            Log.e("Learn English",message);
        }
    }
}
