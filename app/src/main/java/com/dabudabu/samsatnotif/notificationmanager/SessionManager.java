package com.dabudabu.samsatnotif.notificationmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();
    public static final String KEY_USER = "name";
    public static final String KEY_EMAIL = "email";
    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "SamsatAppLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void storeUsername(String name, String email){
        editor.putString(KEY_USER, name);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public HashMap<String,String> getUser(){
        HashMap<String,String> user = new HashMap<>();
        user.put(KEY_USER, pref.getString(KEY_USER,null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        return user;
    }
    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}
