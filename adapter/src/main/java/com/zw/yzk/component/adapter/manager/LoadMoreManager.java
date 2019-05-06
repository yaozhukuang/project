package com.zw.yzk.component.adapter.manager;

import com.zw.yzk.component.adapter.AdapterConstant;
import com.zw.yzk.component.adapter.load.LoadMoreListener;
import com.zw.yzk.component.adapter.load.LoadMoreView;

/**
 * 加载更多管理类
 */
public class LoadMoreManager {

    private int loadState;
    private LoadMoreView loadMoreView;
    private LoadMoreListener listener;
    private boolean loadMoreEnable;

    public LoadMoreManager() {
        this.loadState = AdapterConstant.STATE_DEFAULT;
    }


    /**
     * 设置加载更多监听事件
     *
     * @param listener 更多监听事件
     */
    public void setLoadMoreListener(LoadMoreListener listener) {
        this.listener = listener;
    }

    /**
     * 获取加载更多监听
     */
    public LoadMoreListener getLoadMoreListener() {
        return listener;
    }

    /**
     * 获取加载状态
     *
     * @return 当前加载状态
     */
    public int getLoadState() {
        return loadState;
    }

    /**
     * 设置加载状态
     *
     * @param loadState 加载更多状态
     */
    public void setLoadState(int loadState) {
        this.loadState = loadState;
    }

    /**
     * 设置加载更多视图
     *
     * @param loadMore 加载更多展示
     */
    public void setLoadMoreView(LoadMoreView loadMore) {
        this.loadMoreView = loadMore;
    }

    public LoadMoreView getLoadMoreView() {
        return loadMoreView;
    }

    /**
     * 是否允许自动加载更多
     * @return true： 允许，false：不允许
     */
    public boolean isLoadMoreEnable() {
        return loadMoreEnable;
    }

    /**
     * 设置是否允许加载更多
     * @param loadMoreEnable true： 允许，false：不允许
     */
    public void setLoadMoreEnable(boolean loadMoreEnable) {
        this.loadMoreEnable = loadMoreEnable;
    }

    /**
     * 获取load more item的数目
     * @return load more item的数目
     */
    public int getLoadMoreCount() {
        return loadMoreEnable ? 1: 0;
    }
}
