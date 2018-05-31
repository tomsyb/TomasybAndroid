package com.example.tomasyb.tomasybandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tomasyb.baselib.util.LogUtils;

/**
 * 引导页
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LogUtils.e("没来");
    }
}
