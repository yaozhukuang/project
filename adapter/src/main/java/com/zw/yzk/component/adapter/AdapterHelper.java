package com.zw.yzk.component.adapter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zw.yzk.component.adapter.load.LoadMoreListener;
import com.zw.yzk.component.adapter.load.LoadMoreView;
import com.zw.yzk.component.adapter.refresh.RefreshListener;

public class AdapterHelper {

    private WrapperAdapter adapter;

    public AdapterHelper(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        this.adapter = new WrapperAdapter(recyclerView, adapter);
    }

    /**
     * 根据data的位置获取列表中item的位置
     * @param dataPosition dataPosition
     * @return data对应的item在列表的位置
     */
    public int getItemPosition(int dataPosition) {
        return adapter.getItemPosition(dataPosition);
    }

    /**
     * 获取header
     *
     * @return 返回header
     */
    public View getHeader() {
        return adapter.getHeader();
    }

    /**
     * 获取空白页
     *
     * @return 空白页
     */
    public View getEmptyView() {
        return adapter.getEmptyView();
    }

    /**
     * 获取footer
     *
     * @return footer
     */
    public View getFooter() {
        return adapter.getFooter();
    }

    /**
     * 获取加载更多视图
     *
     * @return loadMoreView
     */
    public View getLoadMoreView() {
        return adapter.getLoadMoreView();
    }

    /**
     * 返回item个数，由于可能存在refresh、header、footer、loading 所以不要使用
     * getItemCount返回数据的个数
     *
     * @return item的个数（不包含header，footer，loading）
     */
    public int getDataCount() {
        return adapter.getDataCount();
    }

    /**
     * 返回item的实际位置
     *
     * @param position 包含了header，footer，loading的位置
     * @return item的实际位置
     */
    public int getDataPosition(int position) {
        return adapter.getDataPosition(position);
    }

    /**
     * 判断当前是否处于下拉刷新状态
     *
     * @return true：正在刷新，false：不处于刷新状态
     */
    public boolean isRefreshing() {
        return adapter.isRefreshing();
    }

    /**
     * 判断当前是否处于上拉加载装
     *
     * @return true: 正在加载， false：不处于加载状态
     */
    public boolean isLoading() {
        return adapter.isLoading();
    }

    /**
     * 下拉或者上拉刷新失败
     */
    public void finishFailed() {
        adapter.finishFailed();
    }

    /**
     * 刷新成功
     */
    public void setRefreshing(boolean refreshing) {
        adapter.setRefreshing(refreshing);
    }

    /**
     * 加载成功
     */
    public void loadSucceed() {
        adapter.loadSucceed();
    }

    /**
     * 加载失败
     */
    public void loadFailed() {
        adapter.loadFailed();
    }

    /**
     * 已经加载所有数据
     */
    public void loadAll() {
        adapter.loadAll();
    }

    /**
     * 设置下拉刷新是否可用，当设置了swipeRefreshLayout后默认可用
     *
     * @param enable: true 可用，false 不可用
     */
    public AdapterHelper setRefreshEnable(boolean enable) {
        adapter.setRefreshEnable(enable);

        return this;
    }

    /**
     * 设置下拉刷新监听事件
     *
     * @param listener 监听事件
     */
    public AdapterHelper setRefreshListener(final RefreshListener listener) {
        adapter.setRefreshListener(listener);

        return this;
    }

    /**
     * 设置设置刷新View
     *
     * @param view SwipeRefreshLayout
     */
    public AdapterHelper refresh(SwipeRefreshLayout view) {
        adapter.refresh(view);

        return this;
    }

    /**
     * 设置列表header
     *
     * @param header header
     */
    public AdapterHelper header(View header) {
        adapter.header(header);

        return this;
    }

    /**
     * 设置列表footer
     *
     * @param footer footer
     */
    public AdapterHelper footer(View footer) {
        adapter.footer(footer);

        return this;
    }

    /**
     * 设置EmptyView
     *
     * @param empty 空列表时展示的视图
     */
    public AdapterHelper empty(View empty) {
        adapter.empty(empty);

        return this;
    }

    /**
     * 设置加载更多View
     *
     * @param view LoadMoreView的实现
     */
    public AdapterHelper loadMore(LoadMoreView view) {
        adapter.loadMore(view);

        return this;
    }

    /**
     * 设置是否允许加载更多
     *
     * @param enable loadMoreEnable true： 允许，false：不允许
     */
    public AdapterHelper setLoadMoreEnable(boolean enable) {
        adapter.setLoadMoreEnable(enable);

        return this;
    }

    /**
     * 设置加载更多监听事件
     *
     * @param listener 加载更多监听事件
     */
    public AdapterHelper setLoadMoreListener(LoadMoreListener listener) {
        adapter.setLoadMoreListener(listener);

        return this;
    }

    /**
     * 完成基本动作（设置spanlookup、默认加载视图等）
     */
    public void build() {
        adapter.build();
    }
}
