package com.zw.yzk.component.network.repository;


import com.zw.yzk.component.network.HttpManager;
import com.zw.yzk.component.network.bean.Task;
import com.zw.yzk.component.network.service.FetchService;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

public abstract class AbstractRepository<T extends Task> implements Repository<T> {


    @Override
    @SuppressWarnings("unchecked")
    public Observable observe(T task) {
        return connectServer(addExtraInfo(task))
                .map(checkServerData())
                .compose(getDataTransformer());
    }

    /**
     * 获取用于请求网络的service
     */
    protected <S> S getService(Class<S> s) {
        return HttpManager.getInstance().get(s);
    }

    /**
     * 获取FetchService
     */
    protected FetchService getService() {
        return getService(FetchService.class);
    }

    /**
     * 请求网络
     */
    protected abstract Observable connectServer(T task);

    /**
     * 给所有请求附加额外信息
     */
    protected T addExtraInfo(T t) {
        return t;
    }

    /**
     * 校验服务器数据
     */
    protected abstract Function<ResponseBody, String> checkServerData();

    /**
     * 服务器数据转换
     */
    protected abstract ObservableTransformer getDataTransformer();

}
