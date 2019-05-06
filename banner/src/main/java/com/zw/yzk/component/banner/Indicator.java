package com.zw.yzk.component.banner;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

public interface Indicator {

    /**
     * 获取指示器
     *
     * @return 单个指示器view
     */
    View getIndicator(Context context, ViewGroup parent);

    /**
     * 设置指示器View的状态
     *
     * @param view      指示器
     * @param isCurrent true：是当前指示器，false：不是当前指示器
     */
    void setViewState(View view, boolean isCurrent);

    /**
     * 设置指示器布局位置
     *
     * @return int（Gravity.TOP、Gravity.CENTER等和他们之间的或运算）
     */
    int getGravity();

    /**
     * 设置指示器布局距离上下左右的距离
     */
    Rect getMargin(Context context);
}
