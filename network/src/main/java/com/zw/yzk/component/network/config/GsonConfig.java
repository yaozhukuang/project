package com.zw.yzk.component.network.config;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zw.yzk.component.network.interceptor.ErrorInterceptor;
import com.zw.yzk.component.network.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GsonConfig implements HttpConfig {

    private Context context;
    private String baseUrl;
    private boolean debug;

    public GsonConfig(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    public GsonConfig setDebug(boolean debug) {
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
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Override
    public String getTag() {
        return "gson_" + String.valueOf(hashCode()) + "_" + baseUrl;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

}