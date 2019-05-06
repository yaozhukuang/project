package com.zw.yzk.component.network.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 下载文件
 */

public interface DownloadService {

    /**
     * 下载文件
     *
     * @param url 文件下载路径
     * @return 返回，可以提取文件流
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
