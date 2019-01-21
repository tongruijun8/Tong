package com.trj.tlib.http2;

import android.content.Context;

import com.trj.tlib.bean.RespBean;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 根据项目的具体情况，可以重写此类
 * @param <T>
 */
public abstract class CCallback<T> implements Callback<RespBean<T>> {

    protected Context context;

    public CCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<RespBean<T>> call, Response<RespBean<T>> response) {
        RespBean<T> respBean = response.body();
        if (null != respBean) {
            String status = respBean.getResult();
            if( status == null || status.equals("")){
                Logger.t(respBean.toString());
                return;
            }
            if (status.equals("1")) {
                onTSuccess(respBean);
            }else if(status.equals("0")){
                onTFail(respBean.getError_no(), respBean.getMsg());
            }else{
                String msg = "";
                onTFail(respBean.getError_no(), msg);
            }
        }else{
            onTError("服务器数据异常");
        }
        onTFinish();
    }

    @Override
    public void onFailure(Call<RespBean<T>> call, Throwable t) {
        onTError(t.getMessage());
        onTFinish();
    }

    public abstract void onTSuccess(RespBean<T> respBean);

    public void onTFail(String code ,String msg){

        if(code == "40012"){
            ToastUtil.showToast(context,"登录异常，请重新登录");
            loginAgain();
        }else if(code == "50003"){
            //程序出错
            ToastUtil.showToast(context,"程序出错");
        }else{
            if(null == msg||msg.equals("")){
                return;
            }
            ToastUtil.showToast(context,msg);
        }

    }

    public void onTError(String msg){
        if(null == msg||msg.equals("")){
            return;
        }
        Logger.t(msg);
    }
    public void onTFinish(){

    }

   protected abstract void loginAgain();


}
