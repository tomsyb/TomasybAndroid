package com.example.tomasyb.tomasybandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tomasyb.baselib.base.BaseActivity;
import com.example.tomasyb.baselib.widget.bottombar.BottomBar;
import com.example.tomasyb.baselib.widget.bottombar.BottomBarTab;
import com.example.tomasyb.tomasybandroid.ui.main.fragment.BookFragment;
import com.example.tomasyb.tomasybandroid.ui.main.fragment.GuideFragment;
import com.example.tomasyb.tomasybandroid.ui.main.fragment.IndexFragment;
import com.example.tomasyb.tomasybandroid.ui.main.fragment.MeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页
 */
@Route(path = "/main/mainActivity")
public class MainActivity extends BaseActivity {

    @BindView(R.id.main_bar)
    BottomBar mainBar;

    private IndexFragment mIndexFragment;
    private GuideFragment mGuideFragment;
    private BookFragment mBookFragment;
    private MeFragment mMeFragment;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initFragment();
        initBottomBar();
    }

    /**
     * 初始化底部
     */
    private void initBottomBar() {
        mainBar
                .addItem(new BottomBarTab(this,R.drawable.tab_home_normal,getString(R.string.main_tab_home)))
                .addItem(new BottomBarTab(this,R.drawable.tab_guide_normal,getString(R.string.main_tab_guide)))
                .addItem(new BottomBarTab(this,R.drawable.tab_book_normal,getString(R.string.main_tab_book)))
                .addItem(new BottomBarTab(this,R.drawable.tab_personal_normal,getString(R.string.main_tab_me)));

        mainBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                switchTo(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (mIndexFragment != null&&mBookFragment !=null&&mGuideFragment !=null&&mMeFragment!=null){
            mIndexFragment= (IndexFragment) getSupportFragmentManager().findFragmentByTag("mIndexFragment");
            mGuideFragment= (GuideFragment) getSupportFragmentManager().findFragmentByTag("mGuideFragment");
            mBookFragment= (BookFragment) getSupportFragmentManager().findFragmentByTag("mBookFragment");
            mMeFragment= (MeFragment) getSupportFragmentManager().findFragmentByTag("mMeFragment");
        }else {
            mIndexFragment= new IndexFragment();
            mGuideFragment= new GuideFragment();
            mBookFragment= new BookFragment();
            mMeFragment= new MeFragment();
            transaction.add(R.id.fl_tab_container,mIndexFragment,"mIndexFragment");
            transaction.add(R.id.fl_tab_container,mGuideFragment,"mGuideFragment");
            transaction.add(R.id.fl_tab_container,mBookFragment,"mBookFragment");
            transaction.add(R.id.fl_tab_container,mMeFragment,"mMeFragment");
        }
        transaction.commit();
        switchTo(currentTabPosition);
        mainBar.setCurrentItem(currentTabPosition);
    }

    /**
     * 匹配跳转
     * @param postion 位置
     */
    private void switchTo(int postion) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (postion){
            case 0://首页
                transaction.show(mIndexFragment);
                transaction.hide(mGuideFragment);
                transaction.hide(mBookFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1://导游
                transaction.hide(mIndexFragment);
                transaction.show(mGuideFragment);
                transaction.hide(mBookFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2://书籍
                transaction.hide(mIndexFragment);
                transaction.hide(mGuideFragment);
                transaction.show(mBookFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3://我的
                transaction.hide(mIndexFragment);
                transaction.hide(mGuideFragment);
                transaction.hide(mBookFragment);
                transaction.show(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;

        }
    }

    @Override
    public void initPresenter() {

    }

}
