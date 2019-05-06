package com.zw.yzk.component.adapter.manager;

import android.view.View;

/**
 * 管理列表footer
 */
public class FooterManager {

    private View footer;

    public int getFooterCount(){
        return footer == null ? 0 : 1;
    }

    public View getFooterView() {
        return footer;
    }

    public void setFooterView(View view) {
        this.footer = view;
    }
}
