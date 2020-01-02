package com.selvaganesh.karadipathinterview.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.selvaganesh.karadipathinterview.AppController;

public class NetworkUtils {
    /**
     * Check the Internet connection available status
     *
     * @return boolean true if the Internet Connection is Available and false otherwise
     */
    public static boolean isConnected() {
        //Connectivity manager instance
        ConnectivityManager manager = (ConnectivityManager) AppController.instance.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Fetch Active internet connection from network info
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        // return the network connection is active or not.
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static boolean isGpsEnabled() {
        LocationManager locationManager = (LocationManager) AppController.instance.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
