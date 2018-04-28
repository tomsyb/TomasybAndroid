package com.example.tomasyb.tomasybandroid.example.callback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tomasyb.tomasybandroid.R;

/**
 * @Author: YanBo.
 * @Date: 2018-4-28.
 * @Describe: 回调使用介绍
 * 两种方式：
 * 1.内部类形式
 * 2.直接实现定义的接口
 */
public class CallBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_back);
        TextCallBack();
    }

    private void TextCallBack() {
        Print print = new Print();
        //print.getName();
    }

}
