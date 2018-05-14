package com.dabudabu.samsatnotif.service;

import android.util.Log;

import com.dabudabu.samsatnotif.notificationmanager.SharedPrefManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIDService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: "+refreshedToken);
        storeToken(refreshedToken);
    }

    private void storeToken(String token) {
        SharedPrefManager.getmInstance(getApplicationContext()).saveDeviceToken(token);
    }
}