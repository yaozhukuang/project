package com.zw.yzk.component.banner;

import android.widget.ImageView;

/**
 * 用于图片加载
 */
public interface Loader<T> {

    /**
     * 加载图片回调
     * @param item 数据item
     * @param position 位置banner中的位置
     * @param image 图片控件，可用于设置点击事件
     */
     void load(T item, ImageView image, int position);
}
