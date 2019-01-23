package com.trj.tlib.app;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.Bugly;

public class InitApplication extends Application {

    public Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    /**
     * 设置Buggly 的 appid
     *
     * @param bugglyAppid
     */
    public void setBugglyAppid(String bugglyAppid) {
        Bugly.init(context, bugglyAppid, false);
    }
}
