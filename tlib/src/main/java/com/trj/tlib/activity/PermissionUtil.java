package com.trj.tlib.activity;

import android.Manifest;
import android.support.annotation.NonNull;

import com.trj.tlib.uils.ToastUtil;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class PermissionUtil<T extends InitActivity> implements EasyPermissions.PermissionCallbacks    {

    public static final int TYPE_CALL_PHONE = 1;
    public static final int TYPE_CAMERA = 2;

    private T activity;

    public PermissionUtil(T t) {
        this.activity = t;
    }

    public void init(int type) {
        if(type == TYPE_CALL_PHONE ){
            String[] perms = {Manifest.permission.CALL_PHONE};
            if (EasyPermissions.hasPermissions(activity, perms)) {
//            Toast.makeText(this, "拨打电话的权限", Toast.LENGTH_LONG).show();
                activity.callPhoneNumber();
            } else {
                // request for both permissions
                EasyPermissions.requestPermissions(activity, Manifest.permission.CALL_PHONE,
                        TYPE_CALL_PHONE, perms);
            }
        }else if(type == TYPE_CAMERA){
            String[] perms = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (EasyPermissions.hasPermissions(activity, perms)) {
                activity.selectImage(1);
            } else {
                EasyPermissions.requestPermissions(activity, Manifest.permission.CAMERA,
                        TYPE_CAMERA, perms);
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (TYPE_CALL_PHONE == requestCode) {
            activity.callPhoneNumber();
        }else if(TYPE_CAMERA == requestCode){
            activity.selectImage(1);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (TYPE_CALL_PHONE == requestCode) {
            ToastUtil.showToast(activity.context, "取消影响拨打电话");
        }else if(TYPE_CAMERA == requestCode){
            ToastUtil.showToast(activity.context, "取消影响拍照功能");
        }
    }

    @Override
    public void onRequestPermissionsResult(int i, @NonNull String[] strings, @NonNull int[] ints) {
        EasyPermissions.onRequestPermissionsResult(i, strings, ints, this);
    }


}
