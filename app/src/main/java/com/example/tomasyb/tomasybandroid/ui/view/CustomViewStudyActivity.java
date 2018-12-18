package com.example.tomasyb.tomasybandroid.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义view 学习
 * 1、屏幕坐标系：以屏幕左上角为原点，向右为X增大向下为Y增大。
 * 2、View坐标系
 */
public class CustomViewStudyActivity extends AppCompatActivity {

    @BindView(R.id.cuspiechart)
    CustomPieChart cuspiechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_study);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        ArrayList<PieChart> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PieChart pieChart = new PieChart("我是"+i,i);
            mList.add(pieChart);
        }
        cuspiechart.setData(mList);
    }
}
