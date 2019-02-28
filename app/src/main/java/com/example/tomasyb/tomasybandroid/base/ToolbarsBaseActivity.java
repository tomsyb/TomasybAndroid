package com.example.tomasyb.tomasybandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tomasyb.baselib.base.AppManager;
import com.example.tomasyb.baselib.util.StatusBarUtil;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-11.13:37
 * @since JDK 1.8
 */

public abstract class ToolbarsBaseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.title_text)
    TextView mTitleName;
    public Unbinder mUnbinder;//黄油刀
    private LinearLayout parentLinearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//无标题
        StatusBarUtil.setColor(this,ContextCompat.getColor(this, R.color.y_main_orange));
        initContentView(R.layout.include_layout_toolbar);
        if (getLayoutId() !=0){
            setContentView(getLayoutId());
            mUnbinder = ButterKnife.bind(this);
            initToolbar();
            initView();
            initData();
            AppManager.getAppManager().addActivity(this);//添加Activity到堆栈
        }
    }



    /**
     * 将baseActivity布局添加到parentLinearLayout
     * @param layoutId
     */
    private void initContentView(int layoutId) {
        //把parentLinearLayout布局添加到viewGroup里实现布局的关联
        ViewGroup viewGroup = findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(parentLinearLayout);
        LayoutInflater.from(this).inflate(layoutId,parentLinearLayout,true);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    /**
     * 将子Activity布局添加到parentLinearLayout
     *
     */
    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID,parentLinearLayout,true);

    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack(){
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    protected boolean isShowBack(){
        return true;
    }

    /**
     * 设置标题文本
     */
    protected abstract String getSubTitle();

    private void initToolbar() {
        if (mToolbar != null){
            mToolbar.setTitle("");
            mTitleName.setText(getSubTitle());
            if (isShowBack()){
                showBack();
            }
        }
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
