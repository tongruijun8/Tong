package com.trj.tlib.manage;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 *  activity管理类
 */
public class ActivityManager{
    private List<Activity> activityList = new LinkedList();
    private static ActivityManager instance;

    private ActivityManager() {
    }

    // 单例模式中获取唯一的ActivityManager实例
    public static ActivityManager getInstance() {
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }
}
