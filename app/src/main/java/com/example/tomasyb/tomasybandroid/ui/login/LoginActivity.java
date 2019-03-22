package com.example.tomasyb.tomasybandroid.ui.login;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.util.AnimationUtils;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.baselib.util.ObjectUtils;
import com.example.tomasyb.baselib.util.SPUtils;
import com.example.tomasyb.baselib.util.StatusBarUtil;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.baselib.widget.KeyboardWatcher;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.common.Constant;

import butterknife.BindView;
import butterknife.OnClick;
import io.agora.yshare.SocialHelper;
import io.agora.yshare.callback.SocialLoginCallback;
import io.agora.yshare.entities.ThirdInfoEntity;

/**
 * 登录界面
 */
@Route(path = "/login/LoginActivity")
public class LoginActivity extends BaseActivity implements KeyboardWatcher.SoftKeyboardStateListener,SocialLoginCallback {
    @BindView(R.id.logo)
    LinearLayout mLogo;
    @BindView(R.id.et_mobile)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.iv_clean_phone)
    ImageView mImgCleanPhone;
    @BindView(R.id.clean_password)
    ImageView mCleanPassword;
    @BindView(R.id.iv_show_pwd)
    ImageView mImgShowPwd;
    @BindView(R.id.body)
    LinearLayout body;

    private boolean flag = false;
    private int screenHeight = 0;//屏幕高度
    private KeyboardWatcher keyboardWatcher;

    /**
     * 三方登录
     */
    private SocialHelper socialHelper;


    @Override
    public void doBeforeSetContentView() {
        super.doBeforeSetContentView();
        StatusBarUtil.setTranslucentForImageView(this,null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
    //用处：qq登录和分享回调，以及微博登录回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && socialHelper != null) {//qq分享如果选择留在qq，通过home键退出，再进入app则不会有回调
            socialHelper.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void initView() {
        socialHelper = SocialUtil.INSTANCE.socialHelper;
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        keyboardWatcher = new KeyboardWatcher(findViewById(Window.ID_ANDROID_CONTENT));
        keyboardWatcher.addSoftKeyboardStateListener(this);
        initListener();
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    /**
     * 添加监听
     */
    private void initListener() {
        mEtAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mImgCleanPhone.getVisibility() == View.GONE) {
                    mImgCleanPhone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mImgCleanPhone.setVisibility(View.GONE);
                }
            }
        });
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mCleanPassword.getVisibility() == View.GONE) {
                    mCleanPassword.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mCleanPassword.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    ToastUtils.showLong("请输入数字或字母");
                    s.delete(temp.length() - 1, temp.length());
                    mEtPassword.setSelection(s.length());
                }
            }
        });
    }



    @OnClick({R.id.close, R.id.iv_clean_phone, R.id.clean_password, R.id.iv_show_pwd,R.id.login_img_qq,R.id.login_img_wechat,R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String account = mEtAccount.getText().toString().trim();
                String psd = mEtPassword.getText().toString().trim();
                if (ObjectUtils.isNotEmpty(account)&&ObjectUtils.isNotEmpty(psd)){
                    if (account.equals("yanb")&&psd.equals("123456")){
                        SPUtils.getInstance().put("account",account);
                        SPUtils.getInstance().put("psd",psd);
                        ARouter.getInstance().build(Constant.ACTIVITY_MAIN).navigation();
                    }else {
                        ToastUtils.showLong("账号或密码错误!");
                    }
                }else if (ObjectUtils.isEmpty(account)){
                    ToastUtils.showLong("账号不能为空!");
                }else if (ObjectUtils.isEmpty(psd)){
                    ToastUtils.showLong("密码不能为空!");
                }
                break;
            case R.id.close://关闭当前页面
                finish();
                break;
            case R.id.iv_clean_phone://清除账号
                mEtAccount.setText("");
                break;
            case R.id.login_img_qq://qq登录
                socialHelper.loginQQ(this,this);
                break;
            case R.id.login_img_wechat://微信登录
                socialHelper.loginWX(this, this);
                break;
            case R.id.clean_password://清除密码
                mEtPassword.setText("");
                break;
            case R.id.iv_show_pwd://显示密码
                if (flag == true) {
                    mEtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mImgShowPwd.setImageResource(R.drawable.ic_eye_close);
                    flag = false;
                } else {
                    mEtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mImgShowPwd.setImageResource(R.drawable.ic_eye_open);
                    flag = true;
                }
                String pwd = mEtPassword.getText().toString();
                if (!TextUtils.isEmpty(pwd))
                    mEtPassword.setSelection(pwd.length());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        keyboardWatcher.removeSoftKeyboardStateListener(this);
        if (socialHelper != null) {
            socialHelper.clear();
        }
    }

    @Override
    public void onSoftKeyboardOpened(int keyboardSize) {
        LogUtils.e("键盘展开");
        int[] location = new int[2];
        body.getLocationOnScreen(location); //获取body在屏幕中的坐标,控件左上角
        int x = location[0];
        int y = location[1];
        Log.e("wenzhihao", "y = " + y + ",x=" + x);
        int bottom = screenHeight - (y + body.getHeight());
        Log.e("wenzhihao", "bottom = " + bottom);
        Log.e("wenzhihao", "con-h = " + body.getHeight());
        if (keyboardSize > bottom) {
            ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", 0.0f, -(keyboardSize - bottom));
            mAnimatorTranslateY.setDuration(300);
            mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
            mAnimatorTranslateY.start();
            AnimationUtils.zoomIn(mLogo, keyboardSize - (bottom+20));
        }
    }

    @Override
    public void onSoftKeyboardClosed() {
        LogUtils.e("键盘隐藏");
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", body.getTranslationY(), 0);
        mAnimatorTranslateY.setDuration(300);
        mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimatorTranslateY.start();
        AnimationUtils.zoomOut(mLogo);
    }

    /**
     * 三方登录回调
     * 失败
     * @param msg
     */
    @Override
    public void socialError(String msg) {
        ToastUtils.showLong(msg);
    }

    /**
     * 成功
     * @param info
     */
    @Override
    public void loginSuccess(ThirdInfoEntity info) {
        if (ObjectUtils.isNotEmpty(info.getOpenId())){
            SPUtils.getInstance().put("qq_img_head",info.getAvatar());
            LogUtils.e(toString(info));
            ARouter.getInstance().build(Constant.ACTIVITY_MAIN).navigation();
        }else {
            ToastUtils.showLong("登录失败!");
        }
    }
    private String toString(ThirdInfoEntity info) {
        return "登录信息 = {" +
                "unionId='" + info.getUnionId() + '\'' +
                ", openId='" + info.getOpenId() + '\'' +
                ", nickname='" + info.getNickname() + '\'' +
                ", sex='" + info.getSex() + '\'' +
                ", avatar='" + info.getAvatar() + '\'' +
                ", platform='" + info.getPlatform() + '\'' +
                '}';
    }

}
