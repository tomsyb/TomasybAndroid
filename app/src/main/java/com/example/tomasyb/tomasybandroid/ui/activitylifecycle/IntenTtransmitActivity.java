package com.example.tomasyb.tomasybandroid.ui.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.tomasybandroid.R;

/**
 * 通信
 */
public class IntenTtransmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inten_ttransmit);
        getData();
    }

    /**
     * 获取值
     */
    private void getData() {
        LogUtils.e("你获取的值是-->"+getIntent().getStringExtra("name"));
    }
}
