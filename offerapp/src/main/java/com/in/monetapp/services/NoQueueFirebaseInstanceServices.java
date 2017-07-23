package com.in.monetapp.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class NoQueueFirebaseInstanceServices extends FirebaseInstanceIdService {
    private static final String TAG = NoQueueFirebaseInstanceServices.class.getSimpleName();


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String fcmToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "FCM Token=" + fcmToken);
        // sendRegistrationToServer(refreshedToken);
    }


}
