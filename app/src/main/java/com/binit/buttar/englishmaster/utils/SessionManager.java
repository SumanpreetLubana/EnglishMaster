package com.binit.buttar.englishmaster.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SessionManager {

    private SharedPreferences pref;
    // Editor for Shared preferences
    private SharedPreferences.Editor editor;
    // Context
    private Context _context;

    // Sharedpref file name
    private static final String PREF_NAME = "MyPref";

    public static final String KEY_IS_AD_FREE = "adFreeVersion";


    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(boolean phoneNumber, String key) {
        if (key.equals(KEY_IS_AD_FREE)) {
            editor.putBoolean(KEY_IS_AD_FREE, phoneNumber);
        }
        // commit changes
        editor.commit();
    }

    /**
     * Get stored session data
     */
    public boolean getUserDetails() {
        return pref.getBoolean(KEY_IS_AD_FREE, false);
    }

}