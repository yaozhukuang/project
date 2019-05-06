package com.zw.yzk.component.network.executor;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolExecutorManager {

    private static final int INITIAL_POOL_SIZE = 4;
    private static final int MAX_POOL_SIZE = 8;
    private static final int KEEP_ALIVE_TIME = 10;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private static PoolExecutorManager instance;
    private ThreadPoolExecutor poolExecutor;

    private PoolExecutorManager() {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        poolExecutor = new ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, workQueue, new JobThreadFactory());
    }

    public static PoolExecutorManager getInstance() {
        if (instance == null) {
            synchronized (PoolExecutorManager.class) {
                if (instance == null) {
                    instance = new PoolExecutorManager();
                }
            }
        }
        return instance;
    }

    public ThreadPoolExecutor getPoolExecutor() {
        return poolExecutor;
    }

    private static class JobThreadFactory implements ThreadFactory {
        private static final String THREAD_NAME = "android_";
        private int counter = 0;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, THREAD_NAME + counter);
        }
    }
}
