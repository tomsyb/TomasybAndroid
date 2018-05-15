package com.example.tomasyb.tomasybandroid.example.custom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tomasyb.tomasybandroid.R;

/**
 *自定义View实例
 * 步骤：
 * 1.自定义View属性
 * 在res/values/下建立attrs在里面定义我们的属性和声明整个样式
 * 2.view构造方法中获取自定义属性TypedArray获取
 * 3.重写onMesure（非必须）
 * 4.重写onDraw
 */
public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }
}
