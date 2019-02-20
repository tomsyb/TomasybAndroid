package com.example.tomasyb.tomasybandroid.ui.tablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.agora.yview.tablayout.SlidingTabLayout;

/**
 * Tablayout的使用
 */
public class TabLayoutUseActivity extends AppCompatActivity {

    @BindView(R.id.tab_slidtablayout)
    SlidingTabLayout mTbLayout;
    @BindView(R.id.tablayout_vp)
    ViewPager mVp;
    private String[] mTitles = {"ios","Android","Java"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_use);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        for (String title : mTitles) {
            mFragments.add(SimpleTabFragment.getInstance(title));
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mVp.setAdapter(mAdapter);
        mTbLayout.setViewPager(mVp,mTitles,this,mFragments);
        //设置红点
        mTbLayout.showDot(1);
        mTbLayout.showMsg(2,4);
        mTbLayout.setMsgMargin(2,0,10);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
