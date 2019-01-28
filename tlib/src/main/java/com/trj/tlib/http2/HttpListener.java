package com.trj.tlib.http2;

public interface HttpListener<B> {

    /**
     * 请求成功，返回成功的对象
     * @param bean
     */
    void onTSuccess(B bean);

    /**
     * 请求成功，返回失败的原因
     * @param errorCode 错误码
     * @param errorMsg 错误原因
     */
    void onTFail(int errorCode, String errorMsg);

    /**
     * 提示方法:针对于每一个请求方法单独实现
     * @param message 提示文字
     */
//    void onTPrompt(String message);

    /**
     * 请求失败
     * @param errorMsg
     */
    void onTError(String errorMsg);

    /**
     * 请求完成
     */
    void onTFinish();

}
