package com.example.foodplanner.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
public class CheckConnection {
    private static Context context;
    public static CheckConnection instance = null;
    private CheckConnection(){}
    public static CheckConnection getInstance(Context contextInput) {
        if (instance == null) {
            context = contextInput;
            instance = new CheckConnection();
        }
        return instance;
    }
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    public Boolean isConnected() {
        return ((connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED));
    }
}
