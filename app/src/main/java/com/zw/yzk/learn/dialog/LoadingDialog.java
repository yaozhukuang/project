package com.zw.yzk.learn.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


import com.zw.yzk.learn.R;

import java.lang.reflect.Field;


public class LoadingDialog extends DialogFragment {

    private ImageView loading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setStyle(STYLE_NO_FRAME, R.style.default_dialog_style);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.dialog_loading_common, container, false);
        startAnimation(view);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //开始加载动画
    private void startAnimation(View view) {
        loading = view.findViewById(R.id.loading);
        view.clearAnimation();

        RotateAnimation animation = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(1200);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);

        loading.startAnimation(animation);
    }

    //停止加载动画
    private void clearAnimation() {
        if (loading == null) {
            return;
        }
        loading.clearAnimation();
    }

    public void disMissDialog() {
        clearAnimation();
        dismissAllowingStateLoss();
    }

}
