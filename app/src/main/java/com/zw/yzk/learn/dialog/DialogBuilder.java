package com.zw.yzk.learn.dialog;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 示例用法
 * new DialogBuilder.Builder(this, R.layout.dialog_select_image)
 * .setAnimation(R.style.bottom_animation)
 * .addItem(DialogBuilder.Item(R.id.album, getString(R.string.album), View.OnClickListener {
 * getCameraPermission()
 * }))
 * .addItem(DialogBuilder.Item(R.id.cancel, getString(R.string.cancel)))
 * .setSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
 * .setGravity(Gravity.BOTTOM)
 * .setCancelable(true)
 * .build().show()
 */
public class DialogBuilder {

    public static class Item {
        private int id;
        private String text;
        private View.OnClickListener listener;
        boolean closeAfterClose;

        public Item(int id, String text, View.OnClickListener listener) {
            this(id, text, listener, true);
        }

        public Item(int id, String text) {
            this(id, text, null, false);
        }

        public Item(int id, String text, boolean closeAfterClose) {
            this(id, text, null, closeAfterClose);
        }

        private Item(int id, String text, View.OnClickListener listener, boolean closeAfterClose) {
            this.id = id;
            this.text = text;
            this.listener = listener;
            this.closeAfterClose = closeAfterClose;
        }
    }

    //对话框属性设置
    private static class AlertParams {
        Context context;
        View view;
        List<Item> itemList;
        //是否可点击区域外取消
        boolean cancelable;
        //动画
        int animationId;
        //显示位置
        int gravity = Gravity.NO_GRAVITY;
        //设置对话框style
        int style;
        //设置大小
        Point size;
        //设置背景黑暗度
        float dimAmount = 0.6f;

        public AlertParams(Context context, View view) {
            this.context = context;
            this.view = view;
            this.itemList = new ArrayList<>();
        }
    }

    public static class Builder {
        private final AlertParams params;

        public Builder(Context context, View view) {
            params = new AlertParams(context, view);
        }

        public Builder(Context context, int layoutId) {
            this(context, LayoutInflater.from(context).inflate(layoutId, null));
        }

        public Builder setItem(Item item) {
            params.itemList.add(item);

            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            params.cancelable = cancelable;

            return this;
        }

        public Builder setAnimation(int animation) {
            params.animationId = animation;

            return this;
        }

        public Builder setGravity(int gravity) {
            params.gravity = gravity;

            return this;
        }

        public Builder setStyle(int style) {
            params.style = style;

            return this;
        }

        public Builder setDimAmount(float dimAmount) {
            params.dimAmount = dimAmount;

            return this;
        }

        public Builder setSize(int width, int height) {
            params.size = new Point(width, height);

            return this;
        }

        public AlertDialog build() {
            final AlertDialog dialog = new AlertDialog
                    .Builder(params.context, params.style)
                    .setView(params.view)
                    .setCancelable(params.cancelable).create();

            Window window = dialog.getWindow();
            if (window != null) {
                //设置背景透明
                window.setBackgroundDrawableResource(android.R.color.transparent);
                window.getDecorView().setPadding(0, 0, 0, 0);
                window.getAttributes().dimAmount = params.dimAmount;
                //设置对话框尺寸
                if (params.size != null) {
                    window.getAttributes().width = params.size.x;
                    window.getAttributes().height = params.size.y;
                } else {
                    window.getAttributes().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    window.getAttributes().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
                //设置展示位置
                window.getAttributes().gravity = params.gravity;
                //设置动画
                window.setWindowAnimations(params.animationId);
            }
            //设置item
            for (final Item item : params.itemList) {
                View view = params.view.findViewById(item.id);
                if (view != null) {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (item.listener != null) {
                                item.listener.onClick(v);
                            }
                            if(item.closeAfterClose) {
                                dialog.dismiss();
                            }
                        }
                    });
                }
                if (view instanceof TextView) {
                    ((TextView) view).setText(item.text);
                }
            }
            return dialog;
        }
    }
}