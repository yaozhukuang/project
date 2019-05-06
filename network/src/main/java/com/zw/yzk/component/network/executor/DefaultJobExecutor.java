package com.zw.yzk.component.network.executor;


import java.util.concurrent.ThreadPoolExecutor;


public class DefaultJobExecutor implements ThreadExecutor {

    private ThreadPoolExecutor threadPoolExecutor;

    public DefaultJobExecutor(ThreadPoolExecutor executor) {

        this.threadPoolExecutor = executor;
    }

    @Override
    public void execute(Runnable runnable) {
        this.threadPoolExecutor.execute(runnable);
    }


}