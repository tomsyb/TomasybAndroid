package com.example.tomasyb.baselib.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.tomasyb.baselib.R;
import com.example.tomasyb.baselib.util.TUtil;
import com.example.tomasyb.baselib.util.ToastUitl;
import com.example.tomasyb.baselib.widget.LoadingDialog;
import com.example.tomasyb.baselib.widget.StatusBarCompat;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

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
/***************使用例子*********************/
//1.mvp模式
//public class SampleActivity extends BaseActivity<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleActivity extends BaseActivity {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}
public abstract class BaseActivity<V extends BasePre, M extends IBaseModel> extends RxAppCompatActivity {
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
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        mPre = TUtil.getT(this,0);
        mModel = TUtil.getT(this,1);
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

    //******************************************************************Toast吐司*********
    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    /**
     * 带图片的toast
     * @param text
     * @param res
     */
    public void showToastWithImg(String text,int res) {
        ToastUitl.showToastWithImg(text,res);
    }

    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {
        ToastUitl.showToastWithImg(getText(R.string.b_net_error).toString(),R.drawable.ic_wifi_off);
    }

    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error,R.drawable.ic_wifi_off);
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
