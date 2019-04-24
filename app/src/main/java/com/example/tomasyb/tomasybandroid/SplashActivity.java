package com.example.tomasyb.tomasybandroid;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;

import com.daqsoft.work_login.Login2Activity;
import com.daqsoft.work_login.LoginActivity;
import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.util.ActivityUtils;
import com.example.tomasyb.baselib.util.ObjectUtils;
import com.example.tomasyb.baselib.util.SPUtils;
import com.example.tomasyb.baselib.util.StatusBarUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 引导页
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.button)
    Button mBtnTime;
    // 设定倒计时时长单位s
    private int time = 5;
    // 是否有账号
    private boolean isHaveAccount = false;
    private Timer mTimer;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 设置倒计时
                case 0:
                    mBtnTime.setText("跳过("+time+")");
                    break;
                // 跳转到主界面
                case 1:
                    ActivityUtils.startActivity(com.daqsoft.work_login.MainActivity.class);
//                    if (isHaveAccount){
//                        ARouter.getInstance().build(Constant.ACTIVITY_MAIN).navigation();
//                    }else {
//                        ARouter.getInstance().build(Constant.ACTIVITY_LOGIN)
//                                .withTransition(R.anim.slide_in_bottom,R.anim.slide_out_bottom)
//                                .navigation(SplashActivity.this);
//                    }
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void doBeforeSetContentView() {
        super.doBeforeSetContentView();
        StatusBarUtil.setTranslucentForImageView(this,null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        if (ObjectUtils.isNotEmpty(SPUtils.getInstance().getString("account"))&&ObjectUtils.isNotEmpty(SPUtils.getInstance().getString("psd"))){
            isHaveAccount = true;
        }else {
            isHaveAccount = false;
        }
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onResume() {
        mTimer = new Timer(true);
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                time--;
                handler.sendEmptyMessage((time == 0 ? 1 : 0));
            }
        }, 1000, 1000);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mTimer.cancel();
        super.onPause();
    }


    @OnClick(R.id.button)
    public void onViewClicked() {
        handler.sendEmptyMessage(1);
    }
}
