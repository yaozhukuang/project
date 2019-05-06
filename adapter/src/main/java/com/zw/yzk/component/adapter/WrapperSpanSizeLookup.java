package com.zw.yzk.component.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class WrapperSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private GridLayoutManager.SpanSizeLookup spanSizeLookup;
    private RecyclerView.Adapter adapter;
    private int spanCount;

    public WrapperSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup,
                                 int spanCount,
                                 RecyclerView.Adapter adapter) {
        this.spanSizeLookup = spanSizeLookup;
        this.adapter = adapter;
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        if (adapter.getItemViewType(position) == AdapterConstant.VIEW_TYPE_ITEM) {
            return spanSizeLookup.getSpanSize(position);
        } else {
            return spanCount;
        }
    }
}