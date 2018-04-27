package com.example.tomasyb.tomasybandroid.example.callback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tomasyb.tomasybandroid.R;

/**
 * 回调使用介绍
 */
public class CallBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_back);
        TextCallBack();
    }

    private void TextCallBack() {
        MyCallBack myCallBack = new MyCallBack();
        myCallBack.setListener(new MyCallBack.onListener() {
            @Override
            public void success(String name) {

            }
        });
    }


}
