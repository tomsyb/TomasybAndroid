package com.example.tomasyb.tomasybandroid;

import android.content.Intent;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.util.StatusBarUtil;
import com.example.tomasyb.tomasybandroid.common.Constant;

/**
 * 引导页
 */
public class SplashActivity extends BaseActivity{

    @Override
    public void doBeforeSetContentView() {
        super.doBeforeSetContentView();
        StatusBarUtil.setTranslucent(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

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
    public IBasePresenter initPresenter() {
        return null;
    }


}
