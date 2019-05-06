package com.zw.yzk.component.network.repository;

import com.zw.yzk.component.network.bean.Task;

import io.reactivex.Observable;

public interface Repository<T extends Task> {
     Observable observe(T t);
}
