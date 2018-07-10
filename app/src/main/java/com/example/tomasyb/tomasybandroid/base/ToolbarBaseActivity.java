package com.example.tomasyb.tomasybandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.tomasyb.baselib.base.BaseActivity;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;

/**
 * Toolbar的BaseActivity页面
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-9.17:22
 * @since JDK 1.8
 */

public abstract class ToolbarBaseActivity extends BaseActivity{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.title_text)
    TextView mTitleName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() !=0){
            initToolbar();
        }
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
}
