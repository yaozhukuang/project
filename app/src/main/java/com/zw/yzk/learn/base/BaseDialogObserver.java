package com.zw.yzk.learn.base;


import android.support.v7.app.AppCompatActivity;

import com.zw.yzk.learn.dialog.LoadingDialog;

public class BaseDialogObserver<T> extends BaseObserver<T> {

    private AppCompatActivity activity;
    private LoadingDialog loadingDialog;

    public BaseDialogObserver(AppCompatActivity activity) {
        this.activity = activity;
        loadingDialog = new LoadingDialog();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoadingDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissLoadingDialog();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        dismissLoadingDialog();
    }

    private void showLoadingDialog() {
        if (activity != null && !activity.isFinishing()
                && !loadingDialog.isAdded() && !loadingDialog.isRemoving()
                && !loadingDialog.isVisible()) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .add(loadingDialog, loadingDialog.getClass().getSimpleName())
                    .commitAllowingStateLoss();
            activity.getSupportFragmentManager().executePendingTransactions();
        }
    }

    private void dismissLoadingDialog() {
        loadingDialog.disMissDialog();
    }

}

