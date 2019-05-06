package com.zw.yzk.component.network.usecase;


import com.zw.yzk.component.network.bean.Task;
import com.zw.yzk.component.network.executor.ExecutionThread;
import com.zw.yzk.component.network.executor.ThreadExecutor;
import com.zw.yzk.component.network.observer.AbstractDownloadObserver;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T extends Task> {

    //逻辑运行的线程
    private ExecutionThread executionThread;
    //执行器
    private ThreadExecutor threadExecutor;
    //RxJava订阅者
    private DisposableObserver observer;

    UseCase(ExecutionThread thread, ThreadExecutor executor) {
        this.executionThread = thread;
        this.threadExecutor = executor;
    }

    public abstract Observable buildObservable(T t);

    @SuppressWarnings("unchecked")
    public void execute(DisposableObserver subscriber, T t) {
        this.observer = subscriber;
        this.buildObservable(t)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(executionThread.getScheduler())
                .subscribe(subscriber);
    }

    @SuppressWarnings("unchecked")
    public void download(AbstractDownloadObserver subscriber, T t) {
        this.observer = subscriber;
        this.buildObservable(t)
                .subscribeOn(Schedulers.io())
                .observeOn(executionThread.getScheduler())
                .subscribe(subscriber);
    }

    /**
     * 解除观察者
     */
    public void dispose() {
        if (observer != null && !observer.isDisposed()) {
            observer.dispose();
        }
    }

    public boolean isDisposed() {
        return observer == null || observer.isDisposed();
    }

}
