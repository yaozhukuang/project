package com.zw.yzk.component.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * @author zhanwei
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    protected SparseArray<View> views;

    public ViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <V extends View> V get(@IdRes int id) {
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (V) view;
    }


}
