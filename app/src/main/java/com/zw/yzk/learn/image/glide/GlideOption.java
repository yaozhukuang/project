package com.zw.yzk.learn.image.glide;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.zw.yzk.learn.R;
import com.zw.yzk.learn.image.Option;

@SuppressLint("CheckResult")
public class GlideOption implements Option<RequestOptions> {

    //默认图片占位和错误展示
    public static final int DEFAULT_IMG_PLACE_HOLDER = R.drawable.ic_default_head;
    public static final int DEFAULT_IMG_ERROR = DEFAULT_IMG_PLACE_HOLDER;
    //默认头像占位和错误展示
    public static final int DEFAULT_HEAD_PLACE_HOLDER = DEFAULT_IMG_PLACE_HOLDER;
    public static final int DEFAULT_HEAD_ERROR = DEFAULT_IMG_PLACE_HOLDER;

    /**
     * 显示圆形，使用传入的占位图和错误图
     */
    public static GlideOption circleCropOf(int holder, int error) {
        return new GlideOption(holder, error).circleCrop();
    }

    /**
     * 显示圆形，使用图片当前图片
     */
    public static GlideOption circleCropOf(ImageView image) {
        return new GlideOption().circleCrop().placeHolder(image.getDrawable()).error(DEFAULT_HEAD_ERROR);
    }

    /**
     * 显示圆形，使用默认的占位图和错误图
     */
    public static GlideOption circleCropOf() {
        return new GlideOption(DEFAULT_HEAD_PLACE_HOLDER, DEFAULT_HEAD_ERROR).circleCrop();
    }

    /**
     * 使用默认占位图和错误图
     */
    public static GlideOption defaultOption() {
        return new GlideOption(DEFAULT_IMG_PLACE_HOLDER, DEFAULT_IMG_ERROR);
    }


    private RequestOptions options = new RequestOptions();

    public GlideOption() {
    }


    public GlideOption(int holder, int error) {
        options.placeholder(holder).error(error);
    }

    /**
     * 设置显示的图片的宽高
     */
    public GlideOption override(int width, int height) {
        options.override(width, height);
        return this;
    }

    /**
     * 设置占位图
     */
    public GlideOption placeHolder(int res) {
        options.placeholder(res);
        return this;
    }

    /**
     * 设置占位图
     */
    public GlideOption placeHolder(Drawable drawable) {
        options.placeholder(drawable);
        return this;
    }

    /**
     * 设置错误图
     */
    public GlideOption error(int res) {
        options.error(res);
        return this;
    }

    /**
     * 设置图片缩放模式centerInside
     */
    public GlideOption centerInside() {
        options.centerInside();
        return this;
    }

    /**
     * 设置图片缩放模式centerCrop
     */
    public GlideOption centerCrop() {
        options.centerCrop();
        return this;
    }

    /**
     * 设置图片缩放模式fitCenter
     */
    public GlideOption fitCenter() {
        options.fitCenter();
        return this;
    }

    /**
     * 设置图片显示为圆形
     */
    public GlideOption circleCrop() {
        options.circleCrop();
        return this;
    }

    @Override
    public RequestOptions getOption() {
        return options;
    }
}
