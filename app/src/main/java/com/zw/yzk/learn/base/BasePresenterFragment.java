package com.zw.yzk.learn.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zw.yzk.learn.presenter.Presenter;

public abstract class BasePresenterFragment<P extends Presenter> extends BaseFragment {

    protected P presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    /**
     * 初始化presenter
     */
    protected abstract P initPresenter();
}
