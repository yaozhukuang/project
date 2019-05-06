package com.zw.yzk.learn.utils;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.zw.yzk.learn.R;

public class ToolbarBuilder {

    private Toolbar toolbar;
    private TextView title;
    private TextView rightText;
    private AppCompatActivity activity;

    public ToolbarBuilder(AppCompatActivity activity) {
        this.activity = activity;
        toolbar = activity.findViewById(R.id.toolBar);
        toolbar.setTitle("");
        title = toolbar.findViewById(R.id.toolbarTitle);
        rightText = toolbar.findViewById(R.id.toolBarRightText);
        activity.setSupportActionBar(toolbar);
    }

    public static ToolbarBuilder builder(AppCompatActivity activity) {
        return new ToolbarBuilder(activity);
    }

    public ToolbarBuilder setTitle(String title) {
        this.title.setText(title);
        return this;
    }

    public ToolbarBuilder setTitle(@StringRes int title) {
        this.title.setText(title);
        return this;
    }

    public ToolbarBuilder setTtitleColor(@ColorInt int color) {
        this.title.setTextColor(color);
        return this;
    }

    public ToolbarBuilder setNavigationIcon(@DrawableRes int resId) {
        this.toolbar.setNavigationIcon(resId);
        return this;
    }

    public ToolbarBuilder setBackgroundColor(@ColorInt int color) {
        this.toolbar.setBackgroundColor(color);
        return this;
    }

    public ToolbarBuilder setRightText(@StringRes int resId) {
        this.rightText.setText(resId);
        this.rightText.setVisibility(View.VISIBLE);
        return this;
    }

    public ToolbarBuilder setRightText(String text) {
        this.rightText.setText(text);
        this.rightText.setVisibility(View.VISIBLE);
        return this;
    }

    public ToolbarBuilder setRightTextColor(@ColorInt int color) {
        this.rightText.setTextColor(color);
        return this;
    }

    public ToolbarBuilder setRightText(@StringRes int resId, View.OnClickListener listener) {
        this.rightText.setText(resId);
        this.rightText.setVisibility(View.VISIBLE);
        this.rightText.setOnClickListener(listener);
        return this;
    }

    public ToolbarBuilder setRightText(String text, View.OnClickListener listener) {
        this.rightText.setText(text);
        this.rightText.setVisibility(View.VISIBLE);
        this.rightText.setOnClickListener(listener);
        return this;
    }

    public Toolbar build() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        return toolbar;
    }
}
