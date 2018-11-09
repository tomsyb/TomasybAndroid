package com.example.tomasyb.tomasybandroid.ui.bottombaruse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.baselib.widget.bottombar.BottomBar;
import com.example.tomasyb.baselib.widget.bottombar.OnTabReselectListener;
import com.example.tomasyb.baselib.widget.bottombar.OnTabSelectListener;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
@Route(path = "BottomBarUseActivity")
public class BottomBarUseActivity extends AppCompatActivity {

    @BindView(R.id.index_bottombar)
    BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar_use);
        ButterKnife.bind(this);
        initBottomBar();
    }

    private void initBottomBar() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                // tabId是底部图标item的id
                ToastUtils.showLong("你点击"+tabId);
            }
        });
        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {
                ToastUtils.showLong("你重复点击了"+tabId);
            }
        });
    }
}
