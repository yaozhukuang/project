package com.zw.yzk.component.network.usecase;


import com.zw.yzk.component.network.bean.Task;
import com.zw.yzk.component.network.executor.DefaultJobExecutor;
import com.zw.yzk.component.network.executor.ExecutionThread;
import com.zw.yzk.component.network.executor.PoolExecutorManager;
import com.zw.yzk.component.network.executor.ThreadExecutor;
import com.zw.yzk.component.network.executor.UIThread;
import com.zw.yzk.component.network.repository.Repository;

import io.reactivex.Observable;

/**
 * @author zhanwei
 */
public class DefaultUseCase<T extends Task>  extends UseCase<T> {

    private Repository repository;

    public DefaultUseCase(Repository repository) {
        this(new UIThread(), new DefaultJobExecutor(PoolExecutorManager.getInstance().getPoolExecutor()), repository);
    }

    public DefaultUseCase(ExecutionThread thread, ThreadExecutor executor, Repository repository) {
        super(thread, executor);
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Observable buildObservable(T t) {
        return repository.observe(t);
    }


}
