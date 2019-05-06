package com.zw.yzk.learn.image.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zw.yzk.learn.image.Loader;
import com.zw.yzk.learn.image.Option;

public class GlideLoader implements Loader<RequestOptions> {

    private static GlideLoader instance;

    private GlideLoader() {
    }

    public static GlideLoader getInstance() {
        if (instance == null) {
            synchronized (GlideLoader.class) {
                instance = new GlideLoader();
            }
        }
        return instance;
    }

    @Override
    public void display(Context context, Object source, ImageView target, Option<RequestOptions> option) {
        Glide.with(context)
                .load(source)
                .apply(option.getOption())
                .into(target);
    }
}
