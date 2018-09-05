package com.example.tomasyb.tomasybandroid;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tomasyb.baselib.base.mvp.BasePresenter;
import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.tomasybandroid.common.Constant;

/**
 * 引导页
 */
public class SplashActivity extends BaseActivity{


    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        ARouter.getInstance().build(Constant.MAIN).navigation();
        finish();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

}
