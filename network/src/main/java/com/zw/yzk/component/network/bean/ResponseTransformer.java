package com.zw.yzk.component.network.bean;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class ResponseTransformer<T> implements ObservableTransformer<String, T> {

    private Class<T> resultClass;
    protected Gson gson;

    public ResponseTransformer(Class<T> resultClass) {
        this.resultClass = resultClass;
        gson = new Gson();
    }

    public ResponseTransformer(Class<T> resultClass, Gson gson) {
        this.resultClass = resultClass;
        this.gson = gson;
    }

    @Override
    public ObservableSource<T> apply(Observable<String> upstream) {
        return upstream.map(new Function<String, T>() {
            @Override
            public T apply(String data) {
                return dealTargetData(gson.fromJson(dealOriginalData(data), resultClass));
            }
        });
    }

    protected String dealOriginalData(String data) {
        return data;
    }

    protected T dealTargetData(T data) {
        return data;
    }

}
