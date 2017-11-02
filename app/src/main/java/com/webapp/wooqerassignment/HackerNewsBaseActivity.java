package com.webapp.wooqerassignment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.squareup.otto.Bus;
import com.webapp.wooqerassignment.utils.AppUtils;
import com.webapp.wooqerassignment.utils.ConnectionState;
import com.webapp.wooqerassignment.utils.OttoClient;

public class HackerNewsBaseActivity extends AppCompatActivity {

    private Bus mBus;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        mBus = OttoClient.getInstance().getOttoBus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerInternetCheckReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    /**
     * Method to register runtime broadcast receiver to show snackbar alert for internet connection..
     */
    private void registerInternetCheckReceiver() {
        IntentFilter internetFilter = new IntentFilter();
        internetFilter.addAction("android.net.wifi.STATE_CHANGE");
        internetFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastReceiver, internetFilter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void setStatusBarColor(int color) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    /**
     * Runtime Broadcast receiver inner class to capture internet connectivity events
     */
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean status =AppUtils.isConnectingToInternet(getApplicationContext());
            int connectionState = status ? ConnectionState.CONNECTED : ConnectionState.NOT_CONNECTED;
            ConnectionState connectionStateObj = new ConnectionState();
            connectionStateObj.setConnectionState(connectionState);
            mBus.post(connectionStateObj);
        }
    };

}
