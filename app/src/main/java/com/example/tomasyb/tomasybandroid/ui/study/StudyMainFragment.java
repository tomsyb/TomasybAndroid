package com.example.tomasyb.tomasybandroid.ui.study;

import android.os.Bundle;

import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.tomasybandroid.R;

/**
 * 学习主Fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-5.18:19
 * @since JDK 1.8
 */

public class StudyMainFragment extends BaseFragment{
    private static final String ARG_TITLE = "title";
    /**
     * 单列获取fragment
     * @param title
     * @return
     */
    public static StudyMainFragment getInstance(String title){
        StudyMainFragment fragment = new StudyMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE,title);
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

    }
}
