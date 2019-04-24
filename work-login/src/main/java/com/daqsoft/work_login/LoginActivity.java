package com.daqsoft.work_login;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {


    @BindView(R2.id.tv_login)
    TextView tvLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        tvLogin.setText("严博");
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

}
