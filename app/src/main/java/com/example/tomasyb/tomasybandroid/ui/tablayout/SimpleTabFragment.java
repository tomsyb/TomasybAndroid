package com.example.tomasyb.tomasybandroid.ui.tablayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.base.mvp.LazyFragment;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.agora.yview.tablayout.SegmentTabLayout;
import io.agora.yview.tablayout.listener.OnTabSelectListener;
import io.agora.yview.tablayout.widget.MsgView;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-2-19.17:52
 * @since JDK 1.8
 */

public class SimpleTabFragment extends LazyFragment {
    @BindView(R.id.stab_1)
    SegmentTabLayout mStab1;
    @BindView(R.id.tl_2)
    SegmentTabLayout mStab2;
    @BindView(R.id.tl_3)
    SegmentTabLayout mStab3;
    @BindView(R.id.tl_4)
    SegmentTabLayout mStab4;
    @BindView(R.id.tl_5)
    SegmentTabLayout mStab5;
    private String[] mTitles = {"首页", "消息"};
    private String[] mTitles2 = {"取消红点", "设置红点","设置蓝点"};
    private String[] mTitles_3 = {"首页", "消息", "联系人", "更多"};
    /**
     * 这个做了添加fragment操作
     */
    private String[] mTitles_4 = {"首页", "消息", "联系人"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    public static SimpleTabFragment getInstance(String title) {
        SimpleTabFragment sf = new SimpleTabFragment();
        return sf;
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void lazyInitView(View view, Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_tab_use;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        initTabLayout();
        addFragment();
        initTabListener();

    }

    /**
     * 设置监听
     */
    private void initTabListener() {
        mStab1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mStab2.setCurrentTab(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mStab2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position){
                    case 0:
                        mStab3.hideMsg(2);
                        mStab3.hideMsg(3);
                        break;
                    case 1:
                        mStab3.showDot(2);
                        break;
                    case 2:
                        mStab3.showDot(3);
                        MsgView msgView = mStab3.getMsgView(3);
                        if (msgView != null) {
                            msgView.setBackgroundColor(Color.parseColor("#6D8FB0"));
                        }
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    /**
     * 添加fragment
     */
    private void addFragment() {
        for (String title : mTitles_4) {
            mFragments.add(SimpleSegmentFragment.getInstance("Switch ViewPager" + title));
        }
        mStab4.setTabData(mTitles_4,getActivity(),R.id.fl_change,mFragments);
    }

    /**
     * 初始化Tablayout
     */
    private void initTabLayout() {
        mStab1.setTabData(mTitles);
        mStab2.setTabData(mTitles2);
        mStab3.setTabData(mTitles_3);
        mStab5.setTabData(mTitles_3);

        mStab1.showDot(1);
    }
}
