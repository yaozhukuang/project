package com.zw.yzk.component.adapter.load;

import android.view.View;

/**
 * 加载更多View接口
 */
public interface LoadMoreView {

    /**
     * 正在加载中View
     */
    View getLoadingView();

    /**
     * 正在加载回调
     */
    void loading();

    /**
     * 加载失败回调
     */
    void loadFailed();

    /**
     * 全部加载完成回调
     */
    void loadComplete();
}
