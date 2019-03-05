package com.example.tomasyb.tomasybandroid;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.util.StatusBarUtil;
import com.example.tomasyb.tomasybandroid.common.Constant;

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
                    ARouter.getInstance().build(Constant.ACTIVITY_LOGIN)
                            .withTransition(R.anim.slide_in_bottom,R.anim.slide_out_bottom)
                            .navigation(SplashActivity.this);
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

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
