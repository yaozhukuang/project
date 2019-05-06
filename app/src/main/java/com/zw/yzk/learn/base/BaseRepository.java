package com.zw.yzk.learn.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.zw.yzk.component.network.exception.CustomException;
import com.zw.yzk.component.network.repository.AbstractRepository;
import com.zw.yzk.learn.gson.StringTypeAdapter;

import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

public abstract class BaseRepository<T extends BaseTask> extends AbstractRepository<T> {

    protected Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new StringTypeAdapter()).create();

    @Override
    protected Function<ResponseBody, String> checkServerData() {
        return new Function<ResponseBody, String>() {
            @Override
            public String apply(ResponseBody body) throws Exception {
                try {
                    String data = body.string();
                    JSONObject json = new JSONObject(data);
                    int errCode = json.optInt("errorCode", -1);
                    String errMsg = json.optString("errorMsg");
                    if(errCode >= 0) {
                        return data;
                    } else {
                        throw new CustomException(errCode, errMsg);
                    }
                } catch (JsonSyntaxException | IOException e) {
                    throw new CustomException(CustomException.PARSE_ERROR);
                }
            }
        };
    }

    @Override
    protected ObservableTransformer getDataTransformer() {
        return null;
    }
}
