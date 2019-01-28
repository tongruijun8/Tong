package com.trj.tlib.http2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 事例类（仅供参考）
 * 此类仅供参考，可根据实际的项目重写此方法
 *
 * 注：需要继承HttpBase类，根据请求的具体类型，选择对应的方法进行复制实现即可
 *
 * 实例化Retrofit
 *
 */
@Deprecated
public class HttpRetrofit extends HttpBase {

    // baseUrl 不能为空
    public static String baseUrl = "http://127.0.0.1:8080/";

    public HttpAPI httpAPI;
    protected volatile static HttpRetrofit mInstance;
    protected volatile static HttpRetrofit mInstanceJson;

    private HttpRetrofit(int requestType) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(genericClient(requestType));
        httpAPI = builder.build().create(HttpAPI.class);
    }

    /**
     * 请求体为键值对
     * @return
     */
    public static HttpRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (HttpRetrofit.class) {
                if (mInstance == null){
                    mInstance = new HttpRetrofit(REQUEST_TYPE_DEFAULT);
                }
            }
        }
        return mInstance;
    }

    /**
     * 请求体为json
     * @return
     */
    public static HttpRetrofit getInstanceJson() {
        if (mInstanceJson == null) {
            synchronized (HttpRetrofit.class) {
                if (mInstanceJson == null){
                    mInstanceJson = new HttpRetrofit(REQUEST_TYPE_JSON);
                }
            }
        }
        return mInstanceJson;
    }

}
