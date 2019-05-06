package com.zw.yzk.component.banner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Banner extends FrameLayout {

    private RecyclerView recyclerView;
    private BannerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayout indicatorGroup;
    private Indicator indicator;
    private boolean autoScroll;
    private int duration;


    public Banner(@NonNull Context context) {
        super(context);

        initView();
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        autoScroll = true;
        duration = 3000;
        adapter = new BannerAdapter();

        //添加recyclerView
        recyclerView = (RecyclerView) LayoutInflater.from(getContext()).
                inflate(R.layout.component_banner_layout_banner, this, false);
        addView(recyclerView);
    }

    /**
     * 添加指示器
     *
     * @param indicator 指示器
     */
    public Banner setIndicator(Indicator indicator) {
        if (indicator == null) {
            throw new IllegalArgumentException("indicator could not be null");
        }
        this.indicator = indicator;

        return this;
    }

    /**
     * 设置图片加载方式
     *
     * @param loader 用于图片加载
     */
    @SuppressWarnings("unchecked")
    public Banner setImageLoader(Loader loader) {
        adapter.setLoader(loader);

        return this;
    }

    /**
     * 是否开启自动滚动，默认开启
     *
     * @param enable true：开启，false：关闭
     */
    public Banner setAutoScroll(boolean enable) {
        autoScroll = enable;

        return this;
    }

    /**
     * 设置轮播时间间隔
     *
     * @param duration 时间间隔
     */
    public Banner setDuration(int duration) {
        this.duration = duration;

        return this;
    }

    /**
     * 添加数据
     *
     * @param list 数据列表
     * @param <T>  数据类型
     */
    @SuppressWarnings("unchecked")
    public <T> Banner setBannerList(List<T> list) {
        adapter.setData(list);

        return this;
    }

    /**
     * 完成轮播图设置
     */
    public void build() {
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        addIndicator();
        //开始滚动
        startScroll();
    }

    /**
     * 添加数据并开始轮播
     *
     * @param list 数据列表
     * @param <T>  数据类型
     */
    @SuppressWarnings("unchecked")
    public <T> void startBanner(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        adapter.setData(list);
        //让列表从中间开始，这样就可以前后滑动
        int count = (adapter.getItemCount() / (2 * adapter.getDataCount())) * adapter.getDataCount();
        recyclerView.scrollToPosition(count);
        //添加indicator
        addIndicator();
        //开始滚动
        startScroll();
    }

    /**
     * 添加指示器
     */
    private void addIndicator() {
        if (indicator == null) {
            return;
        }
        indicatorGroup = new LinearLayout(getContext());
        indicatorGroup.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Rect rect = indicator.getMargin(getContext());
        params.leftMargin = rect.left;
        params.bottomMargin = rect.bottom;
        params.rightMargin = rect.right;
        params.topMargin = rect.top;
        params.gravity = indicator.getGravity();
        indicatorGroup.setLayoutParams(params);

        for (int i = 0; i < adapter.getDataCount(); i++) {
            View view = indicator.getIndicator(getContext(), indicatorGroup);
            view.setSelected(i == 0);
            indicatorGroup.addView(view);
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int childCount = indicatorGroup.getChildCount();
                if (childCount == 0) {
                    return;
                }
                int index = linearLayoutManager.findFirstVisibleItemPosition() % childCount;
                for (int i = 0; i < childCount; i++) {
                    View child = indicatorGroup.getChildAt(i);
                    indicator.setViewState(child, i == index);
                }
            }
        });

        addView(indicatorGroup);
    }

    /**
     * 设置滚动
     */
    private void startScroll() {
        if (!autoScroll || adapter.getDataCount() == 0) {
            return;
        }
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                recyclerView.smoothScrollToPosition(linearLayoutManager.findFirstVisibleItemPosition() + 1);
            }
        }, duration, duration, TimeUnit.MILLISECONDS);
    }

}
