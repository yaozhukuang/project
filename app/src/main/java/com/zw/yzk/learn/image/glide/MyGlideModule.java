package com.zw.yzk.learn.image.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;

/**
 * 对Okhttp对全局配置
 */
public class MyGlideModule extends AppGlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        //使用Okhttp请求网络
        registry.append(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        //设置图片编码方式
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));

        //自定义图片缓存位置和代销
        //setDiskCache(InternalCacheDiskCacheFactory(context, diskSize))  //内存中
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "glide",1024 * 1024 * 100)); //sd卡中

        // 自定义内存和图片池大小
        long memorySize = Runtime.getRuntime().maxMemory() / 8;  // 取1/8最大内存作为最大缓存
        builder.setMemoryCache(new LruResourceCache(memorySize));
        builder.setBitmapPool(new LruBitmapPool(memorySize));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
