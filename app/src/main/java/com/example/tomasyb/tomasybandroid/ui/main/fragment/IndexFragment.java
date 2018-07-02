package com.example.tomasyb.tomasybandroid.ui.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.baselib.base.adapter.BaseFragmentAdapter;
import com.example.tomasyb.baselib.util.TabLayoutUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.bean.IndexTable;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.ui.index.fragment.IndexContentFragment;
import com.example.tomasyb.tomasybandroid.ui.main.contract.IndexContract;
import com.example.tomasyb.tomasybandroid.ui.main.model.IndexModel;
import com.example.tomasyb.tomasybandroid.ui.main.presenter.IndexPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 首页fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.15:50
 * @since JDK 1.8
 */

public class IndexFragment extends BaseFragment {
    @BindView(R.id.index_fg_vp)
    ViewPager mViewPager;
    @BindView(R.id.index_fg_tablayout)
    TabLayout mTab;

    @Override
    protected int getLayoutResource() {
        return R.layout.fg_main_index;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {


    }


}
