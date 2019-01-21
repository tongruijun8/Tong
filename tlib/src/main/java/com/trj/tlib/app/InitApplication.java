package com.trj.tlib.app;

import android.app.Application;
import android.content.Context;

public class InitApplication extends Application {

    public Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
