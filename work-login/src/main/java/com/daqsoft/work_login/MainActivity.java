package com.daqsoft.work_login;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R2.id.tv_main)
    TextView tvMain;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tvMain.setText("SB");
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

}
