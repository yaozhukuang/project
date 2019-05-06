package com.zw.yzk.component.network.observer;



import com.zw.yzk.component.network.exception.ExceptionManager;

import io.reactivex.observers.DisposableObserver;


public abstract class DefaultObserver<T> extends DisposableObserver<T> {

    @Override
    public void onError(Throwable e) {
        ExceptionManager.getInstance().getEHandler().handleException(e);
        e.printStackTrace();
    }
}

