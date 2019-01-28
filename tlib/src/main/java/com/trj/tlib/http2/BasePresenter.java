package com.trj.tlib.http2;

import android.content.Context;
import android.content.SharedPreferences;

import com.trj.demo.bean.Users;
import com.trj.tlib.uils.MD5Utils;
import com.trj.tlib.uils.SharedPreferencesUtils;
import com.trj.tlib.uils.TUtils;
import com.trj.tlib.uils.ToastUtil;

import org.apache.commons.lang3.StringUtils;

import retrofit2.Call;

public class BasePresenter
{

    protected Context context;
    protected HttpRetrofit httpRetrofit;

    public BasePresenter(Context context) {
        this.context = context;
        httpRetrofit = HttpRetrofit.getInstance();
    }



    private void saveUserInfo(){
        SharedPreferences.Editor editor = context.getSharedPreferences(SharedPreferencesUtils.FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(ShareKey.user_name,"");
        editor.commit();
    }

    private void clearUserInfo(){
        SharedPreferences.Editor editor = context.getSharedPreferences(SharedPreferencesUtils.FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(ShareKey.user_name,"");
        editor.commit();
    }




//    protected void postTeacherListBySchId(TCallback<ApproverPListInfo> tCallback) {
//        setPostTime();
//        Call<RespBean<ApproverPListInfo>> call = HttpRetrofit.getInstance().hApi.getTeacherListBySchId();
//        call.enqueue(tCallback);
//    }

//    public void postLogin(String phone, String psw, DCallback<Users> dCallback) {
//        if (StringUtils.isEmpty(phone)) {
//            ToastUtil.showToast(context, "手机不能为空");
//            dCallback.onTFinish();
//        } else if (StringUtils.isEmpty(psw)) {
//            ToastUtil.showToast(context, "手机不能为空");
//            dCallback.onTFinish();
//        } else if (!TUtils.isMobileNO(phone)) {
//            ToastUtil.showToast(context, "请输入正确的手机号");
//            dCallback.onTFinish();
//        } else if (psw.length() < 6 || psw.length() > 16) {
//            ToastUtil.showToast(context, "密码有误，请确认");
//            dCallback.onTFinish();
//        } else {
//            Call<RespBean<Users>> call = httpRetrofit.httpAPI.login(phone, MD5Utils.getMD5(psw));
//            call.enqueue(dCallback);
//        }
//    }


}
