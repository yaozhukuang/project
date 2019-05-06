package com.zw.yzk.learn.base;

import com.zw.yzk.component.network.observer.DefaultObserver;

public class BaseObserver<T> extends DefaultObserver<T> {
    @Override
    public void onNext(T value) {
    }

    @Override
    public void onComplete() {
    }
}
