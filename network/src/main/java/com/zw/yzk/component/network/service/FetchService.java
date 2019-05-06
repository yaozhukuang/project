package com.zw.yzk.component.network.service;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface FetchService {

    @POST("{path}")
    Observable<ResponseBody> postBody(
            @Path(value = "path", encoded = true) String path,
            @HeaderMap Map<String, String> headerMap,
            @Body RequestBody body);

    @FormUrlEncoded
    @POST("{path}")
    Observable<ResponseBody> postField(
            @Path(value = "path", encoded = true) String path,
            @HeaderMap Map<String, String> headerMap,
            @FieldMap Map<String, String> params);

    @POST("{path}")
    Observable<ResponseBody> postQuery(
            @Path(value = "path", encoded = true) String path,
            @HeaderMap Map<String, String> headerMap,
            @QueryMap Map<String, String> params);

    @Multipart
    @POST("{path}")
    Observable<ResponseBody> multipart(
            @Path(value = "path", encoded = true) String path,
            @HeaderMap Map<String, String> headerMap,
            @PartMap Map<String, RequestBody> params);


    @GET("{path}")
    Observable<ResponseBody> get(
            @Path(value = "path", encoded = true) String path,
            @HeaderMap Map<String, String> headerMap,
            @QueryMap Map<String, String> params);

    @PUT("{path}")
    Observable<ResponseBody> put(
            @Path(value = "path", encoded = true) String path,
            @HeaderMap Map<String, String> headerMap,
            @Body RequestBody body);

    @DELETE("{path}")
    Observable<ResponseBody> delete(
            @Path(value = "path", encoded = true) String path,
            @HeaderMap Map<String, String> headerMap,
            @QueryMap Map<String, String> params);
}
