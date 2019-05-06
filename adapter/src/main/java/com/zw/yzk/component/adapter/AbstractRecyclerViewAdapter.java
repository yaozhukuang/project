package com.zw.yzk.component.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanwei
 */
public abstract class AbstractRecyclerViewAdapter<T, H extends ViewHolder> extends RecyclerView.Adapter<H> {

    private List<T> dataList;
    private int layoutId;
    protected Context context;

    public AbstractRecyclerViewAdapter(List<T> dataList, @LayoutRes int layoutId) {
        this.dataList = dataList;
        this.layoutId = layoutId;
    }

    public AbstractRecyclerViewAdapter(@LayoutRes int layoutId) {
        this.dataList = new ArrayList<>();
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public H onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        return createViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull H holder, int position) {
        bind(holder, dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    /**
     * 获取数据列表
     *
     * @return 数据列表
     */
    public List<T> getDataList() {
        return dataList;
    }

    /**
     * 根据position获取数据
     *
     * @param position item position
     */
    public T getData(int position) {
        if (position >= dataList.size()) {
            return null;
        } else {
            return dataList.get(position);
        }
    }

    /**
     * 设置数据列表
     *
     * @param data 列表数据
     */
    public void setData(List<T> data) {
        if (data != null) {
            this.dataList = data;
        } else {
            this.dataList.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * 添加一条数据
     *
     * @param item item数据
     */
    public void add(T item) {
        int start = dataList.size();
        this.dataList.add(item);
        notifyItemInserted(start);
    }

    /**
     * 添加一列数据
     *
     * @param data 数据列表
     */
    public void add(List<T> data) {
        if (data != null) {
            int start = dataList.size();
            this.dataList.addAll(data);
            notifyItemRangeInserted(start, data.size());
        }
    }

    /**
     * 删除item
     *
     * @param item item数据
     */
    public void remove(T item) {
        int position = dataList.indexOf(item);
        dataList.remove(item);
        notifyItemRemoved(position);
    }

    /**
     * 删除一列item
     *
     * @param data 数据列表
     */
    public void remove(List<T> data) {
        int position = dataList.indexOf(data.get(0));
        dataList.removeAll(data);
        notifyItemRangeRemoved(position, data.size());
    }

    /**
     * 清空列表
     */
    public void clear() {
        if (dataList.isEmpty()) {
            return;
        }
        dataList.clear();
        notifyDataSetChanged();
    }

    /**
     * adapter逻辑处理
     *
     * @param holder   view holder
     * @param item     data数据
     * @param position item在数据列表中的位置
     */
    protected abstract void bind(H holder, T item, int position);

    /**
     * 创建ViewHolder，如果H是继承ViewHolder则需要重写这个方法
     */
    @SuppressWarnings("unchecked")
    protected H createViewHolder(View view) {
        return (H) new ViewHolder(view);
    }

}
