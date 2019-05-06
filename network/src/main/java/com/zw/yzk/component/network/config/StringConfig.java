package com.zw.yzk.component.network.config;

import android.content.Context;

import com.zw.yzk.component.network.interceptor.ErrorInterceptor;
import com.zw.yzk.component.network.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class StringConfig implements HttpConfig {

    private Context context;
    private String baseUrl;
    private boolean debug;

    public StringConfig(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    public StringConfig setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }


    @Override
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .cache(new Cache(context.getCacheDir(), 20 * 1024 * 1024))
                .addInterceptor(new LoggingInterceptor().setLevel(debug ? LoggingInterceptor.Level.BODY : LoggingInterceptor.Level.NONE))
                .addInterceptor(new ErrorInterceptor())
                .build();
    }

    @Override
    public Retrofit.Builder getRetrofitBuilder() {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create());
    }

    @Override
    public String getTag() {
        return "string_" + String.valueOf(hashCode()) + "_" + baseUrl;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

}