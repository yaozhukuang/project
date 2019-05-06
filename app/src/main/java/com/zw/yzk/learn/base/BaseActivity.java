package com.zw.yzk.learn.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zw.yzk.learn.utils.ActivityManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //添加Activity到Activity栈
        ActivityManager.getInstance().addActivity(this);
        //初始化导航栏
        initToolbar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将Activity从Activity栈移除
        ActivityManager.getInstance().removeActivity(this);
    }

    /**
     * 获取布局文件ID
     *
     * @return Activity布局文件
     */
    protected abstract int getLayoutId();

    /**
     * 创建导航栏
     */
    protected abstract void initToolbar();

    protected void jump(Class<?> cls) {
        if (cls == null) {
            return;
        }
        startActivity(new Intent(this, cls));
    }
}
