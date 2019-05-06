package com.zw.yzk.learn.exception;

import android.content.Context;
import android.text.TextUtils;

import com.zw.yzk.component.network.exception.CustomException;
import com.zw.yzk.component.network.exception.DefaultHandler;
import com.zw.yzk.learn.R;
import com.zw.yzk.learn.toast.ToastManager;

public class MyExceptionHandler extends DefaultHandler {

    public MyExceptionHandler(Context context) {
        super(context);
    }

    @Override
    protected void dealErrorMessage(String msg, int errorCode) {
        if (TextUtils.isEmpty(msg)) {
            switch (errorCode) {
                case CustomException.UNKNOWN_ERROR:
                    showErrorMessage(context.getResources().getString(R.string.component_network_unknown_error), errorCode);
                    break;
                case CustomException.UNKNOWN_INVALID_TOKEN:
                    break;
                case CustomException.MUST_LOGIN:
                    break;
                default:
                    showErrorMessage(context.getResources().getString(R.string.component_network_connect_error), errorCode);
            }
        } else {
            showErrorMessage(msg, errorCode);
        }
    }

    /**
     * 展示错误信息
     *
     * @param msg 错误信息
     */
    private void showErrorMessage(String msg, int errorCode) {
        String errorInfo = msg;
        if (errorCode != 0) {
            errorInfo = msg + "(" + errorCode + ")";
        }
        ToastManager.getInstance().showToast(context, errorInfo);
    }
}
