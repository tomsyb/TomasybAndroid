package com.example.tomasyb.tomasybandroid;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomasyb.baselib.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 引导页
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_img_center)
    ImageView mImgCenter;
    @BindView(R.id.sp_tv_centent)
    TextView mTvCentent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        mTvCentent.setText("哈哈哈");
    }

    @Override
    public void initPresenter() {

    }
}
