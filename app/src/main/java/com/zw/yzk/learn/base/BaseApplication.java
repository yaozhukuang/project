package com.zw.yzk.learn.base;

import android.app.Application;

import com.zw.yzk.component.network.HttpManager;
import com.zw.yzk.component.network.config.BodyConfig;
import com.zw.yzk.component.network.exception.ExceptionManager;
import com.zw.yzk.component.network.interceptor.LoggingInterceptor;
import com.zw.yzk.learn.exception.MyExceptionHandler;
import com.zw.yzk.learn.greendao.DaoManager;
import com.zw.yzk.learn.utils.LogUtils;

public class BaseApplication extends Application {

    private static boolean TEST = true;

    public static boolean isTEST() {
        return TEST;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {

        initRetrofit();
        initGreenDao();
    }

    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {
        HttpManager.getInstance().setConfig(new BodyConfig(this, "http://www.wanandroid.com/")
                .setDebug(isTEST()).setLogger(new LoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtils.i("okhttp", message);
                    }
                }));
        ExceptionManager.getInstance().setEHandler(new MyExceptionHandler(this));
    }

    /**
     * 初始化GreenDao
     */
    private void initGreenDao() {
        DaoManager.getInstance().init(this);
    }
}
