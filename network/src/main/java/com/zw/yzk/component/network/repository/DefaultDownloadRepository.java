package com.zw.yzk.component.network.repository;

import com.zw.yzk.component.network.HttpManager;
import com.zw.yzk.component.network.bean.DTask;
import com.zw.yzk.component.network.bean.ResponseTransformer;
import com.zw.yzk.component.network.config.DownloadConfig;
import com.zw.yzk.component.network.service.DownloadService;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;


public class DefaultDownloadRepository extends AbstractRepository<DTask> {

    @Override
    @SuppressWarnings("unchecked")
    public Observable observe(DTask task) {
        return connectServer(task);
    }

    @Override
    protected <S> S getService(Class<S> s) {
        DownloadConfig dConfig = new DownloadConfig(HttpManager.getInstance().getConfig().getBaseUrl());
        return HttpManager.getInstance().get(dConfig, s);
    }

    @Override
    protected Observable<ResponseBody> connectServer(DTask task) {
        DownloadService service = getService(DownloadService.class);
        return service.download(task.url);
    }

    @Override
    protected Function<ResponseBody, String> checkServerData() {
        return null;
    }

    @Override
    protected ResponseTransformer getDataTransformer() {
        return null;
    }

}
