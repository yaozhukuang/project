package com.zw.yzk.learn.utils;

import android.support.v7.app.AppCompatActivity;

import java.util.Stack;

public class ActivityManager {

    private static class ActivityManagerHolder {
        public static ActivityManager instance = new ActivityManager();
    }

    private Stack<AppCompatActivity> activityStack;

    private ActivityManager() {
        activityStack = new Stack<>();
    }

    public static ActivityManager getInstance() {
        return ActivityManagerHolder.instance;
    }

    /**
     * 获取最后一个入栈的Activity
     *
     * @return activity
     */
    public AppCompatActivity getTopActivity() {
        return activityStack.lastElement();
    }

    /**
     * 获取指定Activity实例
     *
     * @param cls activity类
     * @return activity
     */
    public AppCompatActivity getActivity(Class<AppCompatActivity> cls) {
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass().getName().equals(cls.getName())) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 添加Activity到栈
     *
     * @param activity 添加对象
     */
    public void addActivity(AppCompatActivity activity) {
        activityStack.add(activity);
    }

    /**
     * 将Activity从栈中移除
     *
     * @param activity 移除对象
     */
    public void removeActivity(AppCompatActivity activity) {
        activityStack.remove(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity 结束Activity
     */
    public void finishActivity(AppCompatActivity activity) {
        if (activity == null) {
            return;
        }
        activityStack.remove(activity);
        activity.finish();
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls 指定Activity
     */
    public void finishActivity(Class<AppCompatActivity> cls) {
        finishActivity(getActivity(cls));
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (AppCompatActivity activity : activityStack) {
            activity.fileList();
        }
        activityStack.clear();
    }
}
