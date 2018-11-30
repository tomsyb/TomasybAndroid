package com.example.tomasyb.tomasybandroid.ui.flow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.baselib.widget.flow.AutoFlowLayout;
import com.example.tomasyb.baselib.widget.flow.FlowAdapter;
import com.example.tomasyb.tomasybandroid.R;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 流式布局
 * AutoFlowLayout 属性：
 *     mFlowLayout.setLineCenter(false);设置是否进行行居中显示
 *     mFlowLayout.setSingleLine(true);是否单行
 *     mFlowLayout.setMaxLines(1);最大行数
 *     mFlowLayout.setMaxLines(Integer.MAX_VALUE);最大行
 *      mFlowLayout.deleteView();删除最后一个view
 *       mFlowLayout.setMultiChecked(true); 是否支持多选
 *
 */
public class FlowLayoutActivity extends AppCompatActivity {
    @BindView(R.id.afl_cotent)
    AutoFlowLayout mFlowLayout;
    @BindView(R.id.afl_cotent2)
    AutoFlowLayout mFlowLayout2;
    // 普通的网格布局场景
    @BindView(R.id.afl_cotent3)
    AutoFlowLayout mFlowLayout3;
    private String[] mData = {"Java", "C++", "Python", "JavaScript", "Ruby", "Swift", "MATLAB",
            "Scratch", "Drat", "ABAP", "COBOL", "Fortran", "Scala", "Lisp",
            "Kotlin", "Erlang", "Groovy", "Scheme", "Rust", "Logo", "Prolog", "LabVIEW"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        // 直接添加
        for (int i = 0; i < 10; i++) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_sub, null);
            TextView tvAttrTag = (TextView) inflate.findViewById(R.id.tv_attr_tag);
            tvAttrTag.setText(mData[i]);
            mFlowLayout.addView(inflate);
        }

        //适配器的方式
        mFlowLayout2.setAdapter(new FlowAdapter(Arrays.asList(mData)) {
            @Override
            public View getView(int position) {
                View item = LayoutInflater.from(FlowLayoutActivity.this).inflate(R.layout.item_sub, null);
                TextView tvAttrTag = (TextView) item.findViewById(R.id.tv_attr_tag);
                tvAttrTag.setText(mData[position]);
                return item;
            }
        });
        //监听itemsetOnItemClickListener及长按监听setOnLongItemClickListener
        mFlowLayout2.setOnItemClickListener(new AutoFlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                ToastUtils.showLong("你点击的是-->"+mData[position]);
            }
        });

        // 普通的网格布局场景
        mFlowLayout3.setAdapter(new FlowAdapter(Arrays.asList(mData)) {
            @Override
            public View getView(int position) {
                View item = LayoutInflater.from(FlowLayoutActivity.this).inflate(R.layout.item_sub, null);
                TextView tvAttrTag = (TextView) item.findViewById(R.id.tv_attr_tag);
                tvAttrTag.setText(mData[position]);
                return item;
            }
        });
    }
}
