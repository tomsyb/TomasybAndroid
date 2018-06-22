package com.example.tomasyb.tomasybandroid.comui;

import android.os.Bundle;

import com.example.tomasyb.baselib.base.BaseActivity;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComUiMainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_com_ui_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }


    @OnClick(R.id.comui_btn_login)
    public void onViewClicked() {
    }
}
