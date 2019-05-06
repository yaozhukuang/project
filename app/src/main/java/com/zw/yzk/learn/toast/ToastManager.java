package com.zw.yzk.learn.toast;


import android.content.Context;


/**
 * Created by zhanwei on 2016/12/26.
 */

public class ToastManager {

    private static ToastManager instance;

    private ToastManager() {

    }

    public static ToastManager getInstance() {
        if (instance == null) {
            synchronized (ToastManager.class) {
                if (instance == null) {
                    instance = new ToastManager();
                }
            }
        }
        return instance;
    }

    /**
     * 显示toast
     *
     * @param context  上下文
     * @param msg      要显示的消息
     */
    public void showToast(Context context, final String msg) {
        Toasty.normal(context, msg).show();
    }

    /**
     * 显示toast
     *
     * @param context  上下文
     * @param msgId      要显示的消息
     */
    public void showToast(Context context, int msgId) {
        Toasty.normal(context, context.getResources().getString(msgId)).show();
    }
}
