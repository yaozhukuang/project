package com.zw.yzk.learn.ui.example;

import android.support.v7.app.AppCompatActivity;

import com.zw.yzk.component.network.usecase.DefaultUseCase;
import com.zw.yzk.learn.base.BaseDialogObserver;
import com.zw.yzk.learn.presenter.Presenter;

public class ExamplePresenter implements Presenter {

    private DefaultUseCase<ExampleTask> eUseCase;
    private ExampleView exampleView;

    ExamplePresenter(ExampleView exampleView) {
        this.exampleView = exampleView;
    }

    @Override
    public void onCreate() {
        eUseCase = new DefaultUseCase<>(new ExampleRepository());
    }

    @Override
    public void onDestroy() {
        eUseCase.dispose();
    }

    public void getExampleData(AppCompatActivity activity, int index) {
        eUseCase.execute(new BaseDialogObserver<ExampleEntity>(activity){

            @Override
            public void onNext(ExampleEntity value) {
                super.onNext(value);
                exampleView.getDataSucceed(value);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                //ExceptionHandler里面已经对错误有一个公共处理（弹出toast提示）,如果需要特殊处理，可以重写这个方法
            }
        }, new ExampleTask(index));
    }
}
