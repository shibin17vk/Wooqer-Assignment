package com.webapp.wooqerassignment;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.firebase.client.Firebase;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 */

public class ApplicationClassContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        configStetho();
    }

    private void configStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
