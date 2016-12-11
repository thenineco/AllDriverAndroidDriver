package com.soberdriver.driverapp;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by zest
 */

public class DriverApp extends Application {

    private static Context context;

    public DriverApp() {
        context = this;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
