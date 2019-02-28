package com.example.tomasyb.tomasybandroid.ui.interview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.tomasyb.baselib.base.mvp.BaseFragment;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.tomasybandroid.R;

/**
 * 列表
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-2-28.17:12
 * @since JDK 1.8
 */

public class InterviewListFragment extends BaseFragment{

    public static InterviewListFragment getInstance() {
        InterviewListFragment sf = new InterviewListFragment();
        return sf;
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_interview_list;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
