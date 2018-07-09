package com.example.tomasyb.tomasybandroid.ui.study;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 学习主Fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-5.18:19
 * @since JDK 1.8
 */

public class StudyMainFragment extends BaseFragment {
    private static final String ARG_TITLE = "title";
    @BindView(R.id.rv)
    RecyclerView mRv;


    /**
     * 单列获取fragment
     *
     * @param title
     * @return
     */
    public static StudyMainFragment getInstance(String title) {
        StudyMainFragment fragment = new StudyMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fg_study_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initData();
        mRv.setLayoutManager(new LinearLayoutManager(mRv.getContext()));
        mRv.setAdapter();
    }

    private void initData() {

    }


}
