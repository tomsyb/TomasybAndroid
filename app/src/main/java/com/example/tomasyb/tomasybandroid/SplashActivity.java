package com.example.tomasyb.tomasybandroid;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tomasyb.baselib.base.BaseActivity;
import com.example.tomasyb.tomasybandroid.common.Constant;

import butterknife.BindView;

/**
 * 引导页
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_img_center)
    ImageView mImgCenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        setTranslanteBar();
        ARouter.getInstance().build(Constant.MAIN).navigation();
        finish();
    }

    @Override
    public void initPresenter() {

    }

}
