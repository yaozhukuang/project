package com.zw.yzk.component.network.executor;


import io.reactivex.Scheduler;


public interface ExecutionThread {
    Scheduler getScheduler();
}
