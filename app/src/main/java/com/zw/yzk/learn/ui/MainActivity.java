package com.zw.yzk.learn.ui;

import android.os.Bundle;
import android.view.View;

import com.zw.yzk.learn.R;
import com.zw.yzk.learn.base.BaseActivity;
import com.zw.yzk.learn.ui.example.ExampleActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ExampleActivity.class);
            }
        });
    }

}
