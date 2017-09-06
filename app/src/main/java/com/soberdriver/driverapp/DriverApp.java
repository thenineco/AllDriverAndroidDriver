package com.soberdriver.driverapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.soberdriver.driverapp.services.chat_service.BackgroundManager;
import com.soberdriver.driverapp.services.chat_service.SocketConnectionImpl;

/**
 * Created by zest
 */

public class DriverApp extends Application {

    private static Context context;
    private BackgroundManager.Listener appActivityListener;
    private SocketConnectionImpl sSocketConnection;

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
        setAppActivityListener();
    }


    private void setAppActivityListener() {
        appActivityListener = new BackgroundManager.Listener() {
            public void onBecameForeground() {
                openSocketConnection();
                Log.i("Websocket", "Became Foreground");
            }

            public void onBecameBackground() {
                closeSocketConnection();
                Log.i("Websocket", "Became Background");
            }
        };
    }

    public void startSocket(String driverId) {
        if (sSocketConnection == null) {
            sSocketConnection = new SocketConnectionImpl(getContext(), driverId);
            BackgroundManager.get(this).registerListener(appActivityListener);
        } else {
            sSocketConnection.setOrderId(driverId);
            openSocketConnection();
        }
    }

    public void closeSocketConnection() {
        sSocketConnection.closeConnection();
    }

    public void openSocketConnection() {
        sSocketConnection.openConnection();
    }
}
