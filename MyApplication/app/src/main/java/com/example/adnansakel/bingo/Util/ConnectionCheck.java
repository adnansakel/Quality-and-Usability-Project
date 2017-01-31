package com.example.adnansakel.bingo.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Adnan Sakel on 1/26/2017.
 */
public class ConnectionCheck {

    private Context _context;

    public ConnectionCheck(Context context){
        this._context = context;
        //Toast.makeText(_context, "From connection check", Toast.LENGTH_LONG).show();
    }

    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            Toast.makeText(_context, "Your device seems to be not connected to internet. Please connect to the internet and try again.", Toast.LENGTH_LONG).show();
            System.out.println("Toastcheck");
        }

        System.out.println("isConnected: " + isConnected);
        return isConnected;
    }

    public boolean isConnectedWithoutToastMessage(){
        ConnectivityManager cm = (ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;

    }

}
