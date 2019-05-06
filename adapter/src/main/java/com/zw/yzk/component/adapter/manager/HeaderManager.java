package com.zw.yzk.component.adapter.manager;

import android.view.View;

/**
 * 管理列表header
 */
public class HeaderManager {

    private View header;

    public int getHeaderCount() {
        return header == null ? 0: 1;
    }

    public View getHeaderView() {
        return header;
    }

    public void setHeaderView(View view) {
        this.header = view;
    }
}
