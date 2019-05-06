package com.zw.yzk.learn.network;

import android.content.Context;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.zw.yzk.component.network.config.BodyConfig;
import com.zw.yzk.component.network.interceptor.ErrorInterceptor;
import com.zw.yzk.component.network.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class CookieConfig extends BodyConfig {
    public CookieConfig(Context context, String baseUrl) {
        super(context, baseUrl);
    }

    @Override
    public OkHttpClient getOkHttpClient() {
        if (logger == null) {
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
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context)))
                .build();
    }
}
