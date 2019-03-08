package com.example.tomasyb.tomasybandroid;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.baselib.util.SPUtils;
import com.example.tomasyb.baselib.util.StatusBarUtil;
import com.example.tomasyb.baselib.widget.bottombar.BottomBar;
import com.example.tomasyb.baselib.widget.bottombar.OnTabSelectListener;
import com.example.tomasyb.tomasybandroid.ui.main.fragment.BookFragment;
import com.example.tomasyb.tomasybandroid.ui.main.fragment.GuideFragment;
import com.example.tomasyb.tomasybandroid.ui.main.index.IndexFragment;
import com.example.tomasyb.tomasybandroid.ui.main.fragment.MeFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * 首页
 * 注意在这里解决fragment 重叠问题
 */
@Route(path = "/main/mainActivity")
public class MainActivity extends BaseActivity {
    @BindView(R.id.main_bottom_bar)
    BottomBar botBar;

    private IndexFragment mIndexFragment;
    private GuideFragment mGuideFragment;
    private BookFragment mBookFragment;
    private MeFragment mMeFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private List<Fragment> fragments;
    int fragment_index = 0;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        if (savedInstanceState != null) {
            fragment_index = savedInstanceState.getInt("index");
            mIndexFragment = (IndexFragment) manager.findFragmentByTag("home1");
            mGuideFragment = (GuideFragment) manager.findFragmentByTag("home2");
            mBookFragment = (BookFragment) manager.findFragmentByTag("home3");
            mMeFragment = (MeFragment) manager.findFragmentByTag("home4");
            fragments.add(mIndexFragment);
            fragments.add(mGuideFragment);
            fragments.add(mBookFragment);
            fragments.add(mMeFragment);
        } else {
            fragmentInit();
        }
        initBottomBar();
    }

    private void fragmentInit() {
        transaction = manager.beginTransaction();
        mIndexFragment = new IndexFragment();
        mGuideFragment = new GuideFragment();
        mBookFragment = new BookFragment();
        mMeFragment = new MeFragment();

        fragments.add(mIndexFragment);
        fragments.add(mGuideFragment);
        fragments.add(mBookFragment);
        fragments.add(mMeFragment);

        transaction.add(R.id.fl_tab_container, mIndexFragment, "home1");
        transaction.add(R.id.fl_tab_container, mGuideFragment, "home2");
        transaction.add(R.id.fl_tab_container, mBookFragment, "home3");
        transaction.add(R.id.fl_tab_container, mMeFragment, "home4");
        transaction.commitAllowingStateLoss();
    }
    /**
     * 选择碎片
     */
    private void switchFragment(int index) {
        transaction = manager.beginTransaction();
        for (int i = 0; i < 4; i++) {
            Fragment fragment = fragments.get(i);
            if (i == index) {
                transaction.show(fragment);
                continue;
            }
            transaction.hide(fragment);
        }
        transaction.commit();
    }

    @Override
    public void initView() {
    }

    @Override
    public void doBeforeSetContentView() {
        super.doBeforeSetContentView();
        /**
         * 在fragment设置全屏
         */
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    /**
     * 初始化底部
     */
    private void initBottomBar() {
        botBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switchTo(tabId);
            }
        });
    }


    private void switchTo(int tab){
        switch (tab){
            case  R.id.tab_index://首页
                fragment_index = 0;
                break;
            case R.id.tab_nearby://附近
                fragment_index = 1;
                break;
            case R.id.tab_time://书籍
                fragment_index = 2;
                break;
            case R.id.tab_me://我的
                fragment_index = 3;
                break;
        }
        switchFragment(fragment_index);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 保存当前页面
        outState.putInt("index", fragment_index);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
