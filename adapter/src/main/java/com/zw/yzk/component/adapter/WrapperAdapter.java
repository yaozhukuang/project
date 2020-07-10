package com.zw.yzk.component.adapter;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zw.yzk.component.adapter.load.DefaultLoadMoreView;
import com.zw.yzk.component.adapter.load.LoadMoreListener;
import com.zw.yzk.component.adapter.load.LoadMoreView;
import com.zw.yzk.component.adapter.manager.EmptyViewManager;
import com.zw.yzk.component.adapter.manager.FooterManager;
import com.zw.yzk.component.adapter.manager.HeaderManager;
import com.zw.yzk.component.adapter.manager.LoadMoreManager;
import com.zw.yzk.component.adapter.refresh.RefreshListener;


/**
 * @author zhanwei
 */
public class WrapperAdapter extends RecyclerView.Adapter {

    private HeaderManager headerManager;
    private FooterManager footerManager;
    private EmptyViewManager emptyViewManager;
    private LoadMoreManager loadMoreManager;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private SwipeRefreshLayout refreshLayout;


    public WrapperAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        initManager();
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.adapter.registerAdapterDataObserver(mDataObserver);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case AdapterConstant.VIEW_TYPE_EMPTY:
                return createViewHolder(emptyViewManager.getEmptyView());
            case AdapterConstant.VIEW_TYPE_HEADER:
                return createViewHolder(headerManager.getHeaderView());
            case AdapterConstant.VIEW_TYPE_FOOTER:
                return createViewHolder(footerManager.getFooterView());
            case AdapterConstant.VIEW_TYPE_LOADING:
                return createViewHolder(loadMoreManager.getLoadMoreView().getLoadingView());
            default:
                return adapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case AdapterConstant.VIEW_TYPE_EMPTY:
            case AdapterConstant.VIEW_TYPE_HEADER:
            case AdapterConstant.VIEW_TYPE_FOOTER:
                break;
            case AdapterConstant.VIEW_TYPE_LOADING:
                loading();
                break;
            default:
                adapter.onBindViewHolder(holder, getDataPosition(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        int dataCount = getDataCount();
        if (dataCount == 0) {
            return headerManager.getHeaderCount()
                    + footerManager.getFooterCount() + emptyViewManager.geEmptyViewCount();
        } else {
            return headerManager.getHeaderCount()
                    + dataCount + footerManager.getFooterCount()
                    + loadMoreManager.getLoadMoreCount();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getDataCount() == 0) {
            if (position < headerManager.getHeaderCount()) {
                return AdapterConstant.VIEW_TYPE_HEADER;
            } else if (position >= headerManager.getHeaderCount() && position < headerManager.getHeaderCount() + emptyViewManager.geEmptyViewCount()) {
                return AdapterConstant.VIEW_TYPE_EMPTY;
            } else {
                return AdapterConstant.VIEW_TYPE_FOOTER;
            }
        }
        if (position < headerManager.getHeaderCount()) {
            return AdapterConstant.VIEW_TYPE_HEADER;
        } else if (position == getItemCount() - loadMoreManager.getLoadMoreCount() - footerManager.getFooterCount()
                && footerManager.getFooterCount() != 0) {
            return AdapterConstant.VIEW_TYPE_FOOTER;
        } else if (position == getItemCount() - loadMoreManager.getLoadMoreCount() && loadMoreManager.isLoadMoreEnable()) {
            return AdapterConstant.VIEW_TYPE_LOADING;
        } else {
            return adapter.getItemViewType(position);
        }
    }
    
    /**
     * 判断item是否是adapter里面的普通item
     * @param position
     * @return
     */
    public boolean isNormalItem(int position) {
        int type = getItemViewType(position);
        return type != AdapterConstant.VIEW_TYPE_HEADER
                && type != AdapterConstant.VIEW_TYPE_EMPTY
                && type != AdapterConstant.VIEW_TYPE_FOOTER
                && type != AdapterConstant.VIEW_TYPE_LOADING;
    }

    /**
     * 获取header
     *
     * @return 返回header
     */
    public View getHeader() {
        return headerManager.getHeaderView();
    }

    /**
     * 获取空白页
     *
     * @return 空白页
     */
    public View getEmptyView() {
        return emptyViewManager.getEmptyView();
    }

    /**
     * 获取footer
     *
     * @return footer
     */
    public View getFooter() {
        return footerManager.getFooterView();
    }

    /**
     * 获取加载更多视图
     *
     * @return loadMoreView
     */
    public View getLoadMoreView() {
        return loadMoreManager.getLoadMoreView() != null ? loadMoreManager.getLoadMoreView().getLoadingView() : null;
    }

    /**
     * 返回item个数，由于可能存在refresh、header、footer、loading 所以不要使用
     * getItemCount返回数据的个数
     *
     * @return item的个数（不包含header，footer，loading）
     */
    public int getDataCount() {
        return adapter.getItemCount();
    }

    /**
     * 返回item在data列表的实际位置
     *
     * @param itemPosition 包含了header，footer，loading的位置
     * @return item在data列表的实际位置
     */
    public int getDataPosition(int itemPosition) {
        return itemPosition - headerManager.getHeaderCount();
    }

    /**
     * 返回data在item列表中位置
     *
     * @param dataPosition 包含了header，footer，loading的位置
     * @return data在item列表中的位置
     */
    public int getItemPosition(int dataPosition) {
        return dataPosition + headerManager.getHeaderCount();
    }

    /**
     * 判断当前是否处于下拉刷新状态
     *
     * @return true：正在刷新，false：不处于刷新状态
     */
    public boolean isRefreshing() {
        return refreshLayout != null && refreshLayout.isRefreshing();
    }

    /**
     * 判断当前是否处于上拉加载装
     *
     * @return true: 正在加载， false：不处于加载状态
     */
    public boolean isLoading() {
        return loadMoreManager.getLoadState() == AdapterConstant.STATE_LOADING;
    }

    /**
     * 下拉或者上拉刷新失败
     */
    public void finishFailed() {
        if (isRefreshing()) {
            setRefreshing(false);
        }
        if (isLoading()) {
            loadFailed();
        }
    }

    /**
     * 设置下拉刷新状态
     *
     * @param refreshing true： 正在刷新， false：刷新完成
     */
    public void setRefreshing(boolean refreshing) {
        if (refreshLayout == null) {
            return;
        }
        refreshLayout.setRefreshing(refreshing);
        setRefreshViewState(AdapterConstant.STATE_DEFAULT);
    }

    /**
     * 加载成功
     */
    public void loadSucceed() {
        recyclerView.getRecycledViewPool().getRecycledView(AdapterConstant.VIEW_TYPE_FOOTER);
        setLoadViewState(AdapterConstant.STATE_DEFAULT);
        //需要考虑到recyclerview item动画时间
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkIfNeedLoading();
            }
        }, recyclerView.getItemAnimator().getChangeDuration());
    }

    /**
     * 加载失败
     */
    public void loadFailed() {
        setLoadViewState(AdapterConstant.STATE_LOAD_FAILED);
    }

    /**
     * 已经加载所有数据
     */
    public void loadAll() {
        setLoadViewState(AdapterConstant.STATE_LOAD_ALL);
    }

    /**
     * 设置下拉刷新是否可用，当设置了swipeRefreshLayout后默认可用
     *
     * @param enable: true 可用，false 不可用
     */
    public WrapperAdapter setRefreshEnable(boolean enable) {
        if (refreshLayout != null) {
            refreshLayout.setEnabled(enable);
        }

        return this;
    }

    /**
     * 设置下拉刷新监听事件
     *
     * @param listener 监听事件
     */
    public WrapperAdapter setRefreshListener(final RefreshListener listener) {
        if (refreshLayout == null) {
            return this;
        }
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listener.onRefresh();
            }
        });

        return this;
    }

    /**
     * 设置SwipeRefreshLayout用于下拉刷新
     *
     * @param refreshLayout 包裹RecyclerView的SwipeRefreshLayout
     */
    public WrapperAdapter refresh(SwipeRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;

        return this;
    }

    /**
     * 设置列表header
     *
     * @param header header
     */
    public WrapperAdapter header(View header) {
        headerManager.setHeaderView(header);

        return this;
    }

    /**
     * 设置列表footer
     *
     * @param footer footer
     */
    public WrapperAdapter footer(View footer) {
        footerManager.setFooterView(footer);

        return this;
    }

    /**
     * 设置EmptyView
     *
     * @param empty 空列表时展示的视图
     */
    public WrapperAdapter empty(View empty) {
        emptyViewManager.setEmptyView(empty);

        return this;
    }

    /**
     * 设置加载更多View
     *
     * @param view LoadMoreView的实现
     */
    public WrapperAdapter loadMore(LoadMoreView view) {
        setLoadMoreEnable(true);
        loadMoreManager.setLoadMoreView(view);

        return this;
    }

    /**
     * 设置是否允许加载更多
     *
     * @param enable loadMoreEnable true： 允许，false：不允许
     */
    public WrapperAdapter setLoadMoreEnable(boolean enable) {
        loadMoreManager.setLoadMoreEnable(enable);

        return this;
    }

    /**
     * 设置加载更多监听事件
     *
     * @param listener 加载更多监听事件
     */
    public WrapperAdapter setLoadMoreListener(LoadMoreListener listener) {
        loadMoreManager.setLoadMoreListener(listener);

        return this;
    }

    /**
     * 完成基本动作（设置spanlookup、默认加载视图等）
     */
    public void build() {
        recyclerView.setAdapter(this);
        recyclerView.setItemViewCacheSize(0);
        if (loadMoreManager.isLoadMoreEnable() && loadMoreManager.getLoadMoreView() == null) {
            loadMore(new DefaultLoadMoreView(recyclerView.getContext()));
        }
        setSpanSize();
    }

    /**
     * 初始化manager
     */
    private void initManager() {
        headerManager = new HeaderManager();
        footerManager = new FooterManager();
        emptyViewManager = new EmptyViewManager();
        loadMoreManager = new LoadMoreManager();
    }

    /**
     * 创建ViewHolder
     */
    private RecyclerView.ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    /**
     * 设置列表每列item数目，当列表是网格布局的时候header、footer、LoadMore都要独占一行
     */
    private void setSpanSize() {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager == null) {
            throw new IllegalArgumentException("RecyclerView must set LayoutManager first");
        }
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new WrapperSpanSizeLookup(gridLayoutManager.getSpanSizeLookup(),
                    gridLayoutManager.getSpanCount(), this));
        }
    }

    /**
     * 正在加载
     */
    private void loading() {
        if (loadMoreManager.getLoadState() != AdapterConstant.STATE_LOAD_ALL
                && loadMoreManager.getLoadState() != AdapterConstant.STATE_LOADING) {
            loadMoreManager.setLoadState(AdapterConstant.STATE_LOADING);
            loadMoreManager.getLoadMoreView().loading();
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    loadMoreManager.getLoadMoreListener().onLoad();
                }
            });
        }
    }

    /**
     * 设置刷新View的状态
     *
     * @param state View状态
     */
    private void setRefreshViewState(int state) {
        if (state == AdapterConstant.STATE_DEFAULT) {
            setLoadViewState(state);
        }
    }

    /**
     * 设置加载更多View的状态
     *
     * @param state View状态
     */
    private void setLoadViewState(int state) {
        loadMoreManager.setLoadState(state);
        if (loadMoreManager.getLoadMoreView() == null) {
            return;
        }
        switch (state) {
            case AdapterConstant.STATE_DEFAULT:
                loadMoreManager.getLoadMoreView().loading();
                break;
            case AdapterConstant.STATE_LOAD_FAILED:
                loadMoreManager.getLoadMoreView().loadFailed();
                break;
            case AdapterConstant.STATE_LOAD_ALL:
                loadMoreManager.getLoadMoreView().loadComplete();
                break;
            default:
                break;
        }
    }

    //检查列表更新后师傅需要继续加载更多
    private void checkIfNeedLoading() {
        //判断如果加载完成之后不满一屏，即正在加载的View还在展示那么继续回调加载更多
        if(loadMoreManager.getLoadState() != AdapterConstant.STATE_DEFAULT) {
            return;
        }
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                if (recyclerView.getChildAt(recyclerView.getChildCount() - 1)
                        == loadMoreManager.getLoadMoreView().getLoadingView()) {
                    loading();
                }
            }
        });

    }


    private final RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            int start = headerManager.getHeaderCount() + positionStart;
            notifyItemRangeInserted(start, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            int start = headerManager.getHeaderCount() + positionStart;
            notifyItemRangeChanged(start, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            int start = headerManager.getHeaderCount() + positionStart;
            notifyItemRangeChanged(start, itemCount, payload);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            int start = headerManager.getHeaderCount() + positionStart;
            notifyItemRangeRemoved(start, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            int start = headerManager.getHeaderCount() + fromPosition;
            notifyItemMoved(start, start + itemCount);
        }
    };
}
