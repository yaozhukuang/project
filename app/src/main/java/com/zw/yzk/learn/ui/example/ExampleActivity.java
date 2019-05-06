package com.zw.yzk.learn.ui.example;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.gyf.immersionbar.ImmersionBar;
import com.zw.yzk.component.banner.Banner;
import com.zw.yzk.component.banner.DefaultIndicator;
import com.zw.yzk.component.banner.Loader;
import com.zw.yzk.learn.R;
import com.zw.yzk.learn.base.BasePresenterActivity;
import com.zw.yzk.learn.image.ImageLoader;
import com.zw.yzk.learn.utils.ToolbarBuilder;

public class ExampleActivity extends BasePresenterActivity<ExamplePresenter> implements ExampleView {

    private Banner banner;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_example;
    }

    @Override
    protected void initToolbar() {
        ToolbarBuilder.builder(this)
                .setTitle("测试")
                .setRightText("获取数据", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getTestData();
                    }
                })
                .build();
    }

    @Override
    protected ExamplePresenter initPresenter() {
        return new ExamplePresenter(this);
    }

    @Override
    public void getDataSucceed(ExampleEntity entity) {
        banner.startBanner(entity.data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBanner();
    }

    private void createBanner() {
        banner = findViewById(R.id.banner);
        banner.setIndicator(new DefaultIndicator())
                .setIndicator(new DefaultIndicator())
                .setImageLoader(new Loader<ExampleEntity.Banner>() {
                    @Override
                    public void load(ExampleEntity.Banner item, ImageView image, int position) {
                        ImageLoader.display(ExampleActivity.this, item.imagePath, image);
                    }
                }).build();
    }

    private void getTestData() {
        if(presenter != null) {
            presenter.getExampleData(this, 1);
        }
    }
}
