package com.example.tomasyb.tomasybandroid.ui.main.index;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tomasyb.baselib.base.mvp.BaseFragment;
import com.example.tomasyb.baselib.refresh.SmartRefreshLayout;
import com.example.tomasyb.baselib.refresh.api.RefreshHeader;
import com.example.tomasyb.baselib.refresh.api.RefreshLayout;
import com.example.tomasyb.baselib.refresh.listener.SimpleMultiPurposeListener;
import com.example.tomasyb.baselib.rvadapter.CommonAdapter;
import com.example.tomasyb.baselib.rvadapter.base.ViewHolder;
import com.example.tomasyb.baselib.util.ActivityUtils;
import com.example.tomasyb.baselib.util.BarUtils;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.baselib.util.ScreenUtils;
import com.example.tomasyb.baselib.util.SizeUtils;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.baselib.widget.viewpager.ComFragmentAdapter;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.addressbook.AddressBookActivity;
import com.example.tomasyb.tomasybandroid.ui.main.CircleFriendsActivity;
import com.example.tomasyb.tomasybandroid.ui.main.contact.IndexContact;
import com.example.tomasyb.tomasybandroid.ui.main.entity.IndexMenu;
import com.example.tomasyb.tomasybandroid.ui.main.index.holder.LocalImageHolderView;
import com.example.tomasyb.tomasybandroid.ui.main.presenter.IndexPresenter;
import com.example.tomasyb.tomasybandroid.ui.main.map.MapInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.agora.yview.banner.ConvenientBanner;
import io.agora.yview.banner.holder.CBViewHolderCreator;
import io.agora.yview.banner.listener.OnItemClickListener;
import io.agora.yview.dialog.BaseDialog;
import io.agora.yview.scrollview.JudgeNestedScrollView;
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
        .view, OnItemClickListener {
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.index_banner)
    ConvenientBanner mBanner;
    @BindView(R.id.slitab)
    SlidingTabLayout mSlidTabLayout;
    @BindView(R.id.slitab_title)
    SlidingTabLayout mSlidTabLayoutTitle;
    @BindView(R.id.view_pager)
    ViewPager mVp;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.scrollview)
    JudgeNestedScrollView scrollView;
    @BindView(R.id.buttonBarLayout)
    ButtonBarLayout buttonBarLayout;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.v_statusbar)
    View mStatusBar;
    private final String[] mTitles = {
            "动态", "文章", "问答"
    };
    // 菜单的弹框
    private BaseDialog mMenuDialog;

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
        initViews();
        initRefreshLayout();
        initBanner();
        mPresenter.getDialogData();
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
        mRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
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
                mOffset = offset / 2;
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
    public void initViews() {
        setStatusBarHeight(BarUtils.getStatusBarHeight());
        mVp.setAdapter(new ComFragmentAdapter(getActivity().getSupportFragmentManager(),
                getFragments()));
        mVp.setOffscreenPageLimit(10);
        mSlidTabLayout.setViewPager(mVp, mTitles);
        mSlidTabLayoutTitle.setViewPager(mVp, mTitles);
        mSlidTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mSlidTabLayoutTitle.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int lastScrollY = 0;
            int h = SizeUtils.dp2px(170);
            int color = ContextCompat.getColor(getActivity().getApplicationContext(), R.color
                    .y_color_main) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int
                    oldScrollX, int oldScrollY) {
                int[] location = new int[2];
                mSlidTabLayout.getLocationOnScreen(location);
                int yPosition = location[1];
                if (yPosition < toolBarPositionY) {
                    mSlidTabLayoutTitle.setVisibility(View.VISIBLE);
                    scrollView.setNeedScroll(false);
                    LogUtils.e("------");
                } else {
                    mSlidTabLayoutTitle.setVisibility(View.GONE);
                    scrollView.setNeedScroll(true);
                    LogUtils.e("------2");
                }
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    buttonBarLayout.setAlpha(1f * mScrollY / h);
                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    mStatusBar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    //ivHeader.setTranslationY(mOffset - mScrollY);
                }
                if (scrollY == 0) {
                    //ivBack.setImageResource(R.drawable.back_white);
                    //ivMenu.setImageResource(R.mipmap.ic_menu_black);
                    ivMenu.setVisibility(View.GONE);

                } else {
                    //ivBack.setImageResource(R.drawable.back_black);
                    //ivMenu.setImageResource(R.mipmap.ic_menu_black);
                    ivMenu.setVisibility(View.VISIBLE);
                }
                lastScrollY = scrollY;
            }
        });
        buttonBarLayout.setAlpha(0);
        toolbar.setBackgroundColor(0);
        mStatusBar.setBackgroundColor(0);
        ivMenu.setVisibility(View.GONE);
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
        }, mPresenter.getLocationBannerData())
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.bga_banner_point_disabled, R.drawable
                        .bga_banner_point_enabled,})
                .setOnItemClickListener(this)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //      .setOnPageChangeListener(this)设置翻页监听
        //        convenientBanner.setManualPageable(false);//设置不能手动影响
    }


    @Override
    public void dealWithViewPager() {
        toolBarPositionY = toolbar.getHeight() + BarUtils.getStatusBarHeight();
        ViewGroup.LayoutParams params = mVp.getLayoutParams();
        params.height = ScreenUtils.getScreenHeight() - toolBarPositionY - mSlidTabLayout
                .getHeight() + 1;
        mVp.setLayoutParams(params);
    }

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(IndexOneFragment.getInstance());
        fragments.add(IndexOneFragment.getInstance());
        fragments.add(IndexOneFragment.getInstance());
        return fragments;
    }

    @Override
    public FragmentActivity getContexts() {
        return getActivity();
    }

    @Override
    public void setDialogData(List<IndexMenu.DataBean> list) {
        mMenuDialog = new BaseDialog(getActivity());
        mMenuDialog.contentView(R.layout.dialog_index_menu)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .gravity(Gravity.RIGHT | Gravity.TOP)
                .animType(BaseDialog.AnimInType.RIGHT)
                .offset((int) ivMenu.getX(), (int) ivMenu.getY())
                .canceledOnTouchOutside(true);
        RecyclerView mRv = (RecyclerView)mMenuDialog.findViewById(R.id.dialog_rv);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(new CommonAdapter<IndexMenu.DataBean>(getActivity(),R.layout.item_only_text,list) {
            @Override
            protected void convert(ViewHolder holder, IndexMenu.DataBean indexMenu, int position) {
                holder.setText(R.id.tvCurrent,indexMenu.getName());
                holder.setOnClickListener(R.id.tvCurrent, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (position){
                            case 0:
                                ActivityUtils.startActivity(AddressBookActivity.class);
                                mMenuDialog.dismiss();
                                break;
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        ToastUtils.showLong("你点击的是" + position);
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


    @OnClick({R.id.tv_head, R.id.tv_circle_friends, R.id.iv_menu, R.id.img_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_head:
                break;
            case R.id.tv_circle_friends:
                ActivityUtils.startActivity(CircleFriendsActivity.class);
                break;
            case R.id.iv_menu:
                mMenuDialog.show();
                //ActivityUtils.startActivity(AddressBookActivity.class);
                break;
            case R.id.img_map:
                ActivityUtils.startActivity(MapInfoActivity.class);
                break;
        }
    }

    /**
     * 设置状态栏高度
     *
     * @param statusBarHeight
     */
    public void setStatusBarHeight(int statusBarHeight) {
        ViewGroup.LayoutParams params = mStatusBar.getLayoutParams();
        params.height = statusBarHeight;
        mStatusBar.setLayoutParams(params);
    }
}
