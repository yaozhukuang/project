package com.zw.yzk.learn.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.zw.yzk.learn.toast.ToastManager;

public class IntentUtils {

    public static void startBrowser(Context context, String url, String errMsg) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (checkIntent(context, intent)) {
            context.startActivity(intent);
        } else {
            ToastManager.getInstance().showToast(context, errMsg);
        }
    }

    public static boolean checkIntent(Context context, Intent intent) {
        return intent == null || intent.resolveActivity(context.getPackageManager()) != null;
    }

}
