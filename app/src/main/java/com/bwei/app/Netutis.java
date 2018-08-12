package com.bwei.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Netutis {

    private  static ConnectivityManager connectivityManager;
    public static  boolean HavaNetWork(Context context){
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info==null ||info.isConnected()){

return false;

            }
            else{

                return true;
            }
    }
}
