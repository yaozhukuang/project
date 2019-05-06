package com.zw.yzk.component.network.request;

import com.google.gson.Gson;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.ByteString;

public  class RequestHelper {

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=UTF-8");
    private static final MediaType MEDIA_TYPE_FILE = MediaType.parse("application/octet-stream");

    public static RequestBody setBody(Object object) {
        return setBody(MEDIA_TYPE_JSON, new Gson().toJson(object));
    }

    public static RequestBody setBody(String body) {
        return setBody(MEDIA_TYPE_JSON, body);
    }

    public static RequestBody setBody(MediaType pattern, String body) {
        return RequestBody.create(pattern, body);
    }

    public static RequestBody setBody(File body) {
        return setBody(MEDIA_TYPE_FILE, body);
    }

    public static RequestBody setBody(MediaType pattern, File body) {
        return RequestBody.create(pattern, body);
    }

    public static RequestBody setBody(byte[] body) {
        return setBody(MEDIA_TYPE_JSON, body);
    }

    public static RequestBody setBody(MediaType pattern, byte[] body) {
        return RequestBody.create(pattern, body);
    }

    public static RequestBody setBody(byte[] body, int offset, int count) {
        return setBody(MEDIA_TYPE_JSON, body, offset, count);
    }

    public static RequestBody setBody(MediaType pattern, byte[] body, int offset, int count) {
        return RequestBody.create(pattern, body, offset, count);
    }

    public static RequestBody setBody(ByteString body) {
        return setBody(MEDIA_TYPE_JSON, body);
    }

    public static RequestBody setBody(MediaType pattern, ByteString body) {
        return RequestBody.create(pattern, body);
    }
    

}
