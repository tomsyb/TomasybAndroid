package com.example.tomasyb.tomasybandroid.ui.main.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomasyb.baselib.base.mvp.BaseFragment;
import com.example.tomasyb.baselib.refresh.SmartRefreshLayout;
import com.example.tomasyb.baselib.refresh.api.RefreshHeader;
import com.example.tomasyb.baselib.refresh.api.RefreshLayout;
import com.example.tomasyb.baselib.refresh.listener.OnMultiPurposeListener;
import com.example.tomasyb.baselib.refresh.listener.SimpleMultiPurposeListener;
import com.example.tomasyb.baselib.util.ScreenUtils;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.main.contact.IndexContact;
import com.example.tomasyb.tomasybandroid.ui.main.index.holder.LocalImageHolderView;
import com.example.tomasyb.tomasybandroid.ui.main.presenter.IndexPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.agora.yview.banner.ConvenientBanner;
import io.agora.yview.banner.holder.CBViewHolderCreator;
import io.agora.yview.banner.holder.Holder;
import io.agora.yview.banner.listener.OnItemClickListener;
import io.agora.yview.tablayout.SlidingTabLayout;
import io.agora.yview.tablayout.listener.OnTabSelectListener;


/**
 * 首页fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.15:50
 * @since JDK 1.8
 */

public class IndexFragment extends BaseFragment<IndexContact.presenter> implements IndexContact
        .view , OnItemClickListener {
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.index_banner)
    ConvenientBanner mBanner;
    @BindView(R.id.slitab)
    SlidingTabLayout mSlidTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mVp;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private MyPagerAdapter mTabAdapter;
    private final String[] mTitles = {
            "动态", "文章", "问答"
    };
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private int mOffset = 0;
    private int mScrollY = 0;
    int toolBarPositionY = 0;
    @Override
    protected int getLayoutId() {
        return R.layout.fg_main_index;
    }

    @Override
    public IndexContact.presenter initPresenter() {
        return new IndexPresenter(this);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        initRefreshLayout();
        initAdapter();
        initBanner();
        initTabLayout();
    }

    @Override
    protected void initData() {

    }



    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void initRefreshLayout() {
        mRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener(){
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }

            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent,
                                       int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset/2;
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initBanner() {
        mBanner.setPages(new CBViewHolderCreator() {
            @Override
            public LocalImageHolderView createHolder(View itemView) {
                return new LocalImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_banner_img;
            }
        },mPresenter.getLocationBannerData())
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.bga_banner_point_disabled,R.drawable.bga_banner_point_enabled,})
                .setOnItemClickListener(this)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //      .setOnPageChangeListener(this)设置翻页监听
        //        convenientBanner.setManualPageable(false);//设置不能手动影响
    }

    @Override
    public void initTabLayout() {
        for (String title : mTitles) {
            mFragments.add(IndexOneFragment.getInstance(title));
        }
        mTabAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        mVp.setAdapter(mTabAdapter);
        mSlidTabLayout.setViewPager(mVp);
        mSlidTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void dealWithViewPager() {
        toolBarPositionY = toolbar.getHeight();
        ViewGroup.LayoutParams params = mVp.getLayoutParams();
        params.height = ScreenUtils.getScreenHeight()- toolBarPositionY - mSlidTabLayout.getHeight() + 1;
        mVp.setLayoutParams(params);
    }

    @Override
    public void onItemClick(int position) {
        ToastUtils.showLong("你点击的是"+position);
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        mBanner.startTurning();
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        mBanner.stopTurning();
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
