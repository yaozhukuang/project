package com.zw.yzk.learn.ui.example;

import com.zw.yzk.component.network.bean.ResponseTransformer;
import com.zw.yzk.learn.base.BaseRepository;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import okhttp3.ResponseBody;

public class ExampleRepository extends BaseRepository<ExampleTask> {

    @Override
    protected Observable<ResponseBody> connectServer(ExampleTask task) {
        String path = "banner/json";
        return getService().get(path, task.headers, task.params);
    }

    @Override
    protected ObservableTransformer getDataTransformer() {
        return new ResponseTransformer<ExampleEntity>(ExampleEntity.class, gson) {
            @Override
            protected String dealOriginalData(String data) {
                //处理原始数据, 没有特殊处理则不需要重写
                return super.dealOriginalData(data);
            }

            @Override
            protected ExampleEntity dealTargetData(ExampleEntity data) {
                //处理序列化后的数据, 没有特殊处理则不需要重写
                return super.dealTargetData(data);
            }
        };
    }
}
