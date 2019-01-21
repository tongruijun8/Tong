package com.trj.tlib.http2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRetrofit<T extends HttpAPI> extends HttpBase {

    private volatile static HttpRetrofit mInstance;
    private volatile static HttpRetrofit mInstanceJson;

    public T httpAPI;

    protected HttpRetrofit(int requestType) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(genericClient(requestType));
        httpAPI = (T) builder.build().create(HttpAPI.class);
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
