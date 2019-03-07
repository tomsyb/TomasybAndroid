package com.example.tomasyb.tomasybandroid;

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

import butterknife.BindView;

/**
 * 首页
 */
@Route(path = "/main/mainActivity")
public class MainActivity extends BaseActivity {
    @BindView(R.id.main_bottom_bar)
    BottomBar botBar;
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
        LogUtils.e("--->"+SPUtils.getInstance().getString("qq_img_head"));
        initFragment();
        initBottomBar();
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

    /**
     * 初始化fragment
     */
    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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
    }

    /**
     * 匹配跳转
     * @param postion 位置
     */
    private void switchTo(int postion) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (postion){
            case R.id.tab_index://首页
                transaction.show(mIndexFragment);
                transaction.hide(mGuideFragment);
                transaction.hide(mBookFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.tab_nearby://附近
                transaction.hide(mIndexFragment);
                transaction.show(mGuideFragment);
                transaction.hide(mBookFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.tab_time://书籍
                transaction.hide(mIndexFragment);
                transaction.hide(mGuideFragment);
                transaction.show(mBookFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.tab_me://我的
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
}
