package com.webapp.wooqerassignment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;

import com.webapp.wooqerassignment.topstories.ui.HackerNewsListActivity;

public class SplashScreenActivity extends HackerNewsBaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setStatusBarColor(ContextCompat.getColor(this,R.color.statusBarColor));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, HackerNewsListActivity.class));
                finish();
            }
        }, 2000);
    }
}
