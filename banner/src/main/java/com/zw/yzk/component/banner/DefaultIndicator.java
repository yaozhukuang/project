package com.zw.yzk.component.banner;

import android.content.Context;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 默认指示器
 */
public class DefaultIndicator implements Indicator {
    @Override
    public View getIndicator(Context context, ViewGroup parent) {
        return LayoutInflater.from(context)
                .inflate(R.layout.component_banner_layout_indicator, parent, false);
    }

    @Override
    public void setViewState(View view, boolean isCurrent) {
        view.setSelected(isCurrent);
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM | Gravity.END;
    }

    @Override
    public Rect getMargin(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return new Rect((int) (15 * density), (int) (10 * density), (int) (15 * density), (int) (10 * density));
    }
}
