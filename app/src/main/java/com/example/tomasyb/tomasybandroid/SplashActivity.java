package com.example.tomasyb.tomasybandroid;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.hardware.Camera;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tomasyb.baselib.base.BaseActivity;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.tomasybandroid.api.ScreenShotTools;
import com.example.tomasyb.tomasybandroid.common.Constant;

import butterknife.BindView;

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
        setTranslanteBar();
        mTvCentent.setText("哈哈哈");

        mImgCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLightOn();
            }
        });

    }

    private Camera m_Camera;
    private void OpenLightOn()    {
        if ( null == m_Camera )
        {
            m_Camera = Camera.open();
        }

        Camera.Parameters parameters = m_Camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        m_Camera.setParameters( parameters );
        m_Camera.autoFocus( new Camera.AutoFocusCallback (){
            public void onAutoFocus(boolean success, Camera camera) {
            }
        });
        m_Camera.startPreview();
    }

    private void CloseLightOff()   {
        if ( m_Camera != null )
        {
            m_Camera.stopPreview();
            m_Camera.release();
            m_Camera = null;
        }
    }
    public void screenShot() {
        boolean result = ScreenShotTools.shotBitmap(this);
        if (result) {
            LogUtils.e("截屏成功");
        }
    }


    private void toMainActivity() {
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(mTvCentent, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(mImgCenter, alpha, scaleX, scaleY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ARouter.getInstance().build(Constant.MAIN).navigation();
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }

    @Override
    public void initPresenter() {

    }

}
