package com.example.tomasyb.tomasybandroid.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 自定义view 学习
 * 1、屏幕坐标系：以屏幕左上角为原点，向右为X增大向下为Y增大。
 * 2、View坐标系
 */
public class CustomViewStudyActivity extends AppCompatActivity {

    @BindView(R.id.canvasview)
    CanvasView canvasview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_study);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_translate, R.id.btn_scale})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_translate:
                canvasview.setmCanvasType(0);
                break;
            case R.id.btn_scale:
                canvasview.setmCanvasType(1);
                break;
        }
    }
}
