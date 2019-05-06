package com.zw.yzk.learn.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zw.yzk.learn.presenter.Presenter;

public abstract class BasePresenterActivity<P extends Presenter> extends BaseActivity {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.onCreate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    /**
     * 初始化presenter
     */
    protected abstract P initPresenter();
}
