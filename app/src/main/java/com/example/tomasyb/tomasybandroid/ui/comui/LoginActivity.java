package com.example.tomasyb.tomasybandroid.ui.comui;

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

import com.example.tomasyb.baselib.base.BaseActivity;
import com.example.tomasyb.baselib.util.AnimationUtils;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.baselib.widget.KeyboardWatcher;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.utils.ShareUtils;

import butterknife.BindView;
import butterknife.OnClick;
import yanb.sharelib.SocialHelper;
import yanb.sharelib.callback.SocialLoginCallback;
import yanb.sharelib.entities.ThirdInfoEntity;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements KeyboardWatcher.SoftKeyboardStateListener {
    @BindView(R.id.logo)
    TextView mLogo;
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

    private SocialHelper socialHelper;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        // 分享工具
        socialHelper = ShareUtils.INSTANCE.socialHelper;
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        keyboardWatcher = new KeyboardWatcher(findViewById(Window.ID_ANDROID_CONTENT));
        keyboardWatcher.addSoftKeyboardStateListener(this);
        initListener();
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

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.close, R.id.iv_clean_phone, R.id.clean_password, R.id.iv_show_pwd,R.id.login_img_qq,R.id.login_img_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close://关闭当前页面
                finish();
                break;
            case R.id.iv_clean_phone://清除账号
                mEtAccount.setText("");
                break;
            case R.id.login_img_qq://qq登录
                socialHelper.loginQQ(this, new SocialLoginCallback() {
                    @Override
                    public void loginSuccess(ThirdInfoEntity info) {
                        LogUtils.e(info.getNickname());
                    }

                    @Override
                    public void socialError(String msg) {

                    }
                });
                break;
            case R.id.login_img_wechat://微信登录
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
            AnimationUtils.zoomIn(mLogo, keyboardSize - bottom);

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


    private String toString(ThirdInfoEntity info) {
        return "登录信息 = {" +
                "unionId='" + info.getUnionId() + '\'' +
                ", openId='" + info.getOpenId() + '\'' +
                ", 名称='" + info.getNickname() + '\'' +
                ", 性别='" + info.getSex() + '\'' +
                ", avatar='" + info.getAvatar() + '\'' +
                ", platform='" + info.getPlatform() + '\'' +
                '}';
    }

    //用处：qq登录和分享回调，以及微博登录回调

    /**
     * 用处：qq登录和分享回调，以及微博登录回调,必须写这个登录的 回调才能起作用
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && socialHelper != null) {//qq分享如果选择留在qq，通过home键退出，再进入app则不会有回调
            socialHelper.onActivityResult(requestCode, resultCode, data);
        }
    }
}
