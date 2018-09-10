package com.example.tomasyb.tomasybandroid.ui.study;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.base.adapter.BaseFragmentAdapter;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.widget.coordinatortablayout.CoordinatorTabLayout;
import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 */
@Route(path = "/study/main/StudyMainActivity")
public class StudyMainActivity extends BaseActivity {

    @BindView(R.id.study_main_vp)
    ViewPager mViewPager;
    @BindView(R.id.coordinatortablayout)
    CoordinatorTabLayout mCoortablayout;
    private int[] mImageArray, mColorArray;

    private List<Fragment> mFragment;
    private List<String> mTitles ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_study_main;
    }


    @Override
    public void initView() {
        initFragments();
        initViewPager();
        mImageArray = new int[]{
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher};
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light};
        mCoortablayout.setTranslucentStatusBar(this)
                .setTitle("学习")
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);

    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(),mFragment,mTitles));
    }

    /**
     * 初始化Fragment
     */
    private void initFragments() {
        mFragment = new ArrayList<>();
        mTitles = new ArrayList<>();
        mTitles.add("Rxjava");
        mTitles.add("Retrofit");
        for (int i = 0; i < mTitles.size(); i++) {
            mFragment.add(StudyMainFragment.getInstance(i));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
