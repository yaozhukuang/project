package com.zw.yzk.component.adapter.load;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zw.yzk.component.adapter.R;


/**
 * 默认加载更多视图
 */
public class DefaultLoadMoreView implements LoadMoreView {

    private View rootView;
    private ProgressBar progress;
    private TextView loadFailed;
    private TextView loadComplete;

    public DefaultLoadMoreView(Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.adapter_layout_load_more, null);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (context.getResources().getDisplayMetrics().density * 80)));

        progress = rootView.findViewById(R.id.progress);
        loadFailed = rootView.findViewById(R.id.loading_fail);
        loadComplete = rootView.findViewById(R.id.load_all);
    }

    @Override
    public View getLoadingView() {
        return rootView;
    }

    @Override
    public void loading() {
        progress.setVisibility(View.VISIBLE);
        loadComplete.setVisibility(View.GONE);
        loadFailed.setVisibility(View.GONE);
    }

    @Override
    public void loadFailed() {
        progress.setVisibility(View.GONE);
        loadComplete.setVisibility(View.GONE);
        loadFailed.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadComplete() {
        progress.setVisibility(View.GONE);
        loadComplete.setVisibility(View.VISIBLE);
        loadFailed.setVisibility(View.GONE);
    }
}
