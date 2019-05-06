package com.zw.yzk.component.network.config;



import com.zw.yzk.component.network.interceptor.ErrorInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class DownloadConfig implements HttpConfig {

    private String baseUrl;

    public DownloadConfig(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new ErrorInterceptor())
                .build();
    }

    @Override
    public Retrofit.Builder getRetrofitBuilder() {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    @Override
    public String getTag() {
        return "download_" + String.valueOf(hashCode()) + "_" + baseUrl;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

}