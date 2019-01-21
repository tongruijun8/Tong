package com.trj.tlib.uils;

import android.Manifest;
import android.support.annotation.NonNull;

import com.trj.tlib.activity.InitActivity;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class PermissionUtil<T extends InitActivity> implements EasyPermissions.PermissionCallbacks    {

    /**
     * 位置权限
     */
    final int LOCATION  = 1001;
    final int CALL_PHONE = 1002;

    public static final int TYPE_CALL_PHONE = 1;

    private T activity;

    public PermissionUtil(T t) {
        this.activity = t;
    }

    public void init(int type){
        if(type == TYPE_CALL_PHONE ){
            String[] perms = {Manifest.permission.CALL_PHONE};
            if (EasyPermissions.hasPermissions(activity, perms)) {
//            Toast.makeText(this, "拨打电话的权限", Toast.LENGTH_LONG).show();
               activity.callPhoneNumber();
            } else {
                // request for both permissions
                EasyPermissions.requestPermissions(activity, Manifest.permission.CALL_PHONE,
                        CALL_PHONE, perms);
            }
        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (CALL_PHONE == requestCode) {
            activity.callPhoneNumber();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (CALL_PHONE == requestCode) {
            ToastUtil.showToast(activity.context, "取消影响拨打电话");
        }
    }

    @Override
    public void onRequestPermissionsResult(int i, @NonNull String[] strings, @NonNull int[] ints) {
        EasyPermissions.onRequestPermissionsResult(i, strings, ints, this);
    }


}
