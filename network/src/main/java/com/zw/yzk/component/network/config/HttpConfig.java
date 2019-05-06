package com.zw.yzk.component.network.config;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public interface HttpConfig {

    /**
     * 配置kHttpClient
     */
    OkHttpClient getOkHttpClient();

    /**
     * 配置RetrofitBuilder
     */
    Retrofit.Builder getRetrofitBuilder();

    /**
     * 设置tag
     */
    String getTag();

    /**
     * 获取base url
     */
    String getBaseUrl();
}
