package com.zw.yzk.component.adapter.manager;

import android.view.View;

/**
 * 管理EmptyView
 */
public class EmptyViewManager {

    private View empty;

    public int geEmptyViewCount(){
        return empty == null ? 0 : 1;
    }

    public View getEmptyView() {
        return empty;
    }

    public void setEmptyView(View view) {
        this.empty = view;
    }

}
