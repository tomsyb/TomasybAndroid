package com.example.tomasyb.tomasybandroid.ui.tablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tomasyb.baselib.widget.viewpager.ComPagerWithTitleAdapter;
import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.agora.yview.tablayout.CommonTabLayout;
import io.agora.yview.tablayout.SlidingTabLayout;
import io.agora.yview.tablayout.listener.CustomTabEntity;

/**
 * Tablayout的使用
 */
public class TabLayoutUseActivity extends AppCompatActivity {

    @BindView(R.id.tab_slidtablayout)
    SlidingTabLayout mTbLayout;
    @BindView(R.id.tablayout_vp)
    ViewPager mVp;
    @BindView(R.id.tab_bottom)
    CommonTabLayout mCommonTab;
    private String[] mTitles = {"ios","Android","Java","math"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ComPagerWithTitleAdapter mAdapter;
    private int[] mIconUnselectIds = {
            R.drawable.ic_yichexiao, R.drawable.ic_yichutuan_normal,
            R.drawable.ic_chutuanqian_normal, R.drawable.ic_yijieshu_normal};
    private int[] mIconSelectIds = {
            R.drawable.ic_yichexiao_selected, R.drawable.ic_yichutuan_selected,
            R.drawable.ic_chutuanqian_selected, R.drawable.ic_yijieshu_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
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
        mAdapter = new ComPagerWithTitleAdapter(getSupportFragmentManager(),mFragments,mTitles);
        mVp.setAdapter(mAdapter);
        mTbLayout.setViewPager(mVp,mTitles,this,mFragments);
        //设置红点
        mTbLayout.showDot(1);
        mTbLayout.showMsg(2,4);
        mTbLayout.setMsgMargin(2,0,10);
        initBottom();
    }

    /**
     * 初始化底部
     */
    private void initBottom() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mCommonTab.setTabData(mTabEntities);
    }

}
