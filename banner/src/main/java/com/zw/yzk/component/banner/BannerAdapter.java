package com.zw.yzk.component.banner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter<T> extends RecyclerView.Adapter<BannerAdapter.Holder> {

    private List<T> data = new ArrayList<>();
    private Loader<T> loader;

    public void setData(List<T> data) {
        this.data.clear();
        if (data != null) {
            this.data = data;
        }
        notifyDataSetChanged();
    }

    public void setLoader(Loader<T> loader) {
        this.loader = loader;
    }

    public int getDataCount() {
        return data.size();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_banner_li_banner, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (data.isEmpty()) {
            return;
        }
        ImageView imageView = (ImageView) holder.itemView;
        int index = position % data.size();
        loader.load(data.get(index), imageView, index);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
