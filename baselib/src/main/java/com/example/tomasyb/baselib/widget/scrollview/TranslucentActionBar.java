package com.example.tomasyb.baselib.widget.scrollview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tomasyb.baselib.R;

/**
 * 功能
 * Android沉浸式状态栏 + scrollView顶部伸缩 + actionBar渐变
 * @author 严博
 * @version 1.0.0
 * @date 2018-11-17.13:55
 * @since JDK 1.8
 */

public final class TranslucentActionBar extends LinearLayout {
    /**
     *
     * 以下是控件
     */
    public TextView mTvTitle;// 标题
    private View mStatusBar;//状态栏
    private LinearLayout mRootView;
    public TranslucentActionBar(Context context) {
        this(context,null);
    }

    public TranslucentActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TranslucentActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init() {
        setOrientation(HORIZONTAL);// 设置横向
        View contentView = inflate(getContext(), R.layout.include_title_bar, this);
        // 下面找控件
        mTvTitle = (TextView) contentView.findViewById(R.id.lay_actionbar_title);
        mStatusBar = (View) contentView.findViewById(R.id.v_statusbar);
        mRootView = (LinearLayout)contentView.findViewById(R.id.lay_transroot);
    }

    /**
     * 设置状态栏高度
     * @param statusBarHeight
     */
    public void setStatusBarHeight(int statusBarHeight){
        ViewGroup.LayoutParams params = mStatusBar.getLayoutParams();
        params.height = statusBarHeight;
        mStatusBar.setLayoutParams(params);
    }
    /**
     * 设置是否需要渐变
     */
    public void setNeedTranslucent() {
        setNeedTranslucent(true, false);
    }
    /**
     * 设置是否需要渐变，并且隐藏标题
     * @param translucent
     * @param titleInitVisible
     */
    public void setNeedTranslucent(boolean translucent,boolean titleInitVisible){
        if (translucent){
            mRootView.setBackgroundDrawable(null);
        }
        if (!titleInitVisible){
            mTvTitle.setVisibility(GONE);
        }
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title){
        if (!TextUtils.isEmpty(title)){
            mTvTitle.setText(title);
        }else {
            mTvTitle.setVisibility(GONE);
        }
    }


}
