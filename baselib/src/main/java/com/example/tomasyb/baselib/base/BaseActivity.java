package com.example.tomasyb.baselib.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import com.example.tomasyb.baselib.R;
import com.example.tomasyb.baselib.util.ObjectUtils;
import com.example.tomasyb.baselib.widget.LoadingDialog;
import com.example.tomasyb.baselib.widget.StatusBarCompat;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity基类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-5-30.10:23
 * @since JDK 1.8
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    private Toolbar mToolbar;
    public V mPre;
    public M mModel;
    public Context mContext;
    public Unbinder mUnbinder;//黄油刀
    private boolean isConfigChange = false;//屏幕是否改变

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isConfigChange = false;
        doBeforeSetContentView();
        setContentView(getLayoutId());
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        mPre = ObjectUtils.getT(this,0);
        mModel = ObjectUtils.getT(this,1);
        if (mPre !=null){
            mPre.mContext = this;
        }
        this.initView();
        this.initPresenter();
    }

    //******************************************************************子类实现*********
    public abstract int getLayoutId();//获取布局ID
    public abstract void initView();//初始化
    public abstract void initPresenter();//初始化Presenter

    /**
     * 设置布局前设置
     */
    private void doBeforeSetContentView() {
        AppManager.getAppManager().addActivity(this);//添加Activity到堆栈
        requestWindowFeature(Window.FEATURE_NO_TITLE);//无标题
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置竖屏
        setStatusBarColor();
    }

    //******************************************************************设置状态栏*********

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void setStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.b_main_orange));
    }

    /**
     * 提供外部设置状态栏颜色（4.4以上系统有效）
     *
     * @param color
     */
    protected void setStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void setTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }


    //******************************************************************跳转相关*********
    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls){
        startActivity(cls,null);
    }
    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }
    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    //******************************************************************加载框相关*********
    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(this);
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChange=true;
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPre !=null)
            mPre.onDestroy();
        if (!isConfigChange){
            AppManager.getAppManager().finishActivity(this);
        }
        mUnbinder.unbind();
    }

}
