package com.trj.tlib.http2;

import android.content.Context;
import android.content.Intent;

import com.trj.tlib.activity.GuidePageActivity;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.SharedPreferencesUtils;
import com.trj.tlib.uils.ToastUtil;

import org.apache.commons.lang3.StringUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 事例类（仅供参考）
 * 此类仅供参考，可根据实际的项目重写此方法
 *
 * 注：需要继承Callback类
 *
 * @param <B> 返回数据的对象类
 */
@Deprecated
public abstract class HttpCallback<B> implements Callback<RespBean<B>>,HttpListener<B> {

    private Context context;

    public HttpCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<RespBean<B>> call, Response<RespBean<B>> response) {
        RespBean<B> respBean = response.body();
        if (null != respBean) {
            String msgStr = respBean.getMsg();
            if(StringUtils.isEmpty(msgStr)){
                msgStr = "";
            }
            int status = respBean.getState();
            if (status == 1) {
                onTSuccess(respBean.getData());
            }else{
                onTFail(respBean.getErrorCode(), msgStr);
            }
        }else{
            onTError("服务器数据异常");
        }
        onTFinish();
    }

    @Override
    public void onFailure(Call<RespBean<B>> call, Throwable t) {
        onTError(t.getMessage());
        onTFinish();
    }

    @Override
    public abstract void onTSuccess(B bean);

    @Override
    public void onTFail(int errorCode, String errorMsg) {
        //事例代码
//        if (errorCode == ErrorCodeInfo.token_error) {
//            ToastUtil.showToast(context, errorMsg);
//            context.startActivity(new Intent(context, GuidePageActivity.class));
//        }else{
//            ToastUtil.showToast(context, errorMsg);
//        }
    }

    @Override
    public void onTError(String errorMsg) {
        if (StringUtils.isEmpty(errorMsg)) {
            errorMsg = "请求失败";
        }
        Logger.e("HttpCallback","errorMsg = " + errorMsg);
    }

    @Override
    public abstract void onTFinish();



}
