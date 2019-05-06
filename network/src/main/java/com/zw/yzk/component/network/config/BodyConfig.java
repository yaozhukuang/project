package com.zw.yzk.component.network.config;

import android.content.Context;

import com.zw.yzk.component.network.interceptor.ErrorInterceptor;
import com.zw.yzk.component.network.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class BodyConfig implements HttpConfig {

    protected Context context;
    protected String baseUrl;
    protected boolean debug;
    protected LoggingInterceptor.Logger logger;

    public BodyConfig(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    public BodyConfig setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public BodyConfig setLogger(LoggingInterceptor.Logger logger) {
        this.logger = logger;
        return this;
    }


    @Override
    public OkHttpClient getOkHttpClient() {
        if(logger == null) {
            logger = LoggingInterceptor.Logger.DEFAULT;
        }
        LoggingInterceptor.Level level = debug ? LoggingInterceptor.Level.BODY : LoggingInterceptor.Level.NONE;

        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .cache(new Cache(context.getCacheDir(), 20 * 1024 * 1024))
                .addInterceptor(new LoggingInterceptor(logger).setLevel(level))
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
        return "body_" + String.valueOf(hashCode()) + "_" + baseUrl;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

}