package com.zw.yzk.component.network.exception;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.text.ParseException;

import plugin.test.yzk.zw.com.http.R;
import retrofit2.HttpException;

public class DefaultHandler implements EHandler {

    protected Context context;
    private CustomException exception;

    public DefaultHandler(Context context) {
        this.context = context.getApplicationContext();
    }

    public CustomException getException() {
        return exception;
    }

    @Override
    public void handleException(Throwable e) {
        if (e instanceof CustomException) {
            exception = (CustomException) e;
            String msg = "";
            if (TextUtils.isEmpty(exception.getErrMsg())) {
                switch (exception.getErrCode()) {
                    case CustomException.PARSE_ERROR:
                        msg = context.getResources().getString(R.string.component_network_parse_error);
                        break;
                    case CustomException.UNKNOWN_HOST:
                        msg = context.getResources().getString(R.string.component_network_unknown_host_error);
                        break;
                    case CustomException.SOCKET_ERROR:
                        msg = context.getResources().getString(R.string.component_network_socket_error);
                        break;
                    case CustomException.SOCKET_TIMEOUT:
                        msg = context.getResources().getString(R.string.component_network_socket_timeout);
                        break;
                    case CustomException.IO_ERROR:
                        msg = context.getResources().getString(R.string.component_network_io_error);
                        break;
                    default:
                        break;
                }
                exception.setErrMsg(msg);
            }
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            exception = new CustomException(httpException.code(), context.getResources().getString(R.string.component_network_network_error));
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            exception = new CustomException(CustomException.PARSE_ERROR, context.getResources().getString(R.string.component_network_parse_error));
        } else if (e instanceof ConnectException) {
            exception = new CustomException(CustomException.CONNECT_ERROR, context.getResources().getString(R.string.component_network_connect_error));
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            exception = new CustomException(CustomException.SSLHANDSHAKE_ERROR, context.getResources().getString(R.string.component_network_ssl_error));
        } else {
            exception = new CustomException(CustomException.UNKNOWN_ERROR, context.getResources().getString(R.string.component_network_unknown_error));
        }
        dealErrorMessage(exception.getErrMsg(), exception.getErrCode());
    }

    /**
     * 展示错误信息
     *
     * @param msg 错误信息
     */
    protected void dealErrorMessage(String msg, int errorCode) {
        String errorInfo = msg;
        if (errorCode != 0) {
            errorInfo = msg + "(" + errorCode + ")";
        }
        Log.e("DefaultHandler", errorInfo);
    }
}