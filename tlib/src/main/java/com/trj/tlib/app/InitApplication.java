package com.trj.tlib.app;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.Bugly;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.SharedPreferencesUtils;

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

    protected void initAppParameter(){
        //sharePreferences 的参数
        SharedPreferencesUtils.FILE_NAME = "tong";
//        初始化打印的参数
        Logger.LOG_ENABLE = true;//是否开启打印
        Logger.tag = "tong";//打印的参数名



    }

}
