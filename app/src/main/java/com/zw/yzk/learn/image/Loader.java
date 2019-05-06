package com.zw.yzk.learn.image;

import android.content.Context;
import android.widget.ImageView;

public interface  Loader<T> {
    void display(Context context, Object source, ImageView target, Option<T> option);
}
