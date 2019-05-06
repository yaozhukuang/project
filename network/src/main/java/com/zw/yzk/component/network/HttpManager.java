package com.zw.yzk.component.network;

import android.text.TextUtils;

import com.zw.yzk.component.network.config.HttpConfig;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;


public class HttpManager {
    private static HttpManager instance;

    private Map<String, Retrofit> builderMap = new HashMap<>();
    private HttpConfig mConfig;


    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    public <T> T get(Class<T> service) {
        return getRetrofit(mConfig).create(service);
    }

    public <T> T get(HttpConfig config, Class<T> service) {
        return getRetrofit(config).create(service);
    }

    /**
     * 设置网络配置
     */
    public void setConfig(HttpConfig config) {
        this.mConfig = config;
    }

    /**
     * 获取配置的config
     */
    public HttpConfig getConfig() {
        return mConfig;
    }

    /**
     * 根据url和config获取retrofit对象
     */
    private Retrofit getRetrofit(HttpConfig config) {
        if (config == null) {
            throw new IllegalStateException("HttpConfig must be set");
        }
        if (TextUtils.isEmpty(config.getTag())) {
            throw new IllegalStateException("tag can not be null");
        }
        if (builderMap.get(config.getTag()) != null) {
            return builderMap.get(config.getTag());
        }

        Retrofit retrofit = config
                .getRetrofitBuilder()
                .client(config.getOkHttpClient()).build();
        builderMap.put(config.getTag(), retrofit);
        return retrofit;
    }

}