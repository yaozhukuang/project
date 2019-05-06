package com.zw.yzk.learn.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.zw.yzk.learn.image.glide.GlideLoader;
import com.zw.yzk.learn.image.glide.GlideOption;

public class ImageLoader {

    public static void display(Context context, Object source, ImageView target) {
        display(context, source, target, GlideOption.defaultOption());
    }

    public static void displayCircle(Context context, Object source, ImageView target) {
        display(context, source, target, GlideOption.circleCropOf());
    }

    public static void display(Context context, Object source, ImageView target, Option option) {
        if (source instanceof String) {
            String url = (String) source;
            if (url.startsWith("http")) {
                //加载网络图片
                LazyHeaders.Builder builder = new LazyHeaders.Builder();
                //可以添加自定义builder
                load(context, new GlideUrl(url, builder.build()), target, option);
            } else {
                //加载本地图片
                load(context, source, target, option);
            }
        } else {
            //加载本地图片
            load(context, source, target, option);
        }
    }

    public static void load(Context context, Object source, ImageView target, Option option) {
        GlideLoader.getInstance().display(context, source, target, option);
    }
}
