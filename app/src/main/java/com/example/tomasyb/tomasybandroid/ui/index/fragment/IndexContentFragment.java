package com.example.tomasyb.tomasybandroid.ui.index.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.tomasyb.baselib.base.mvp.BaseFragment;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.bean.NewsSummary;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页滑动内容Fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-7.15:03
 * @since JDK 1.8
 */

public class IndexContentFragment extends BaseFragment{

    private List<NewsSummary> mDatas = new ArrayList<>();
    private int mStartPage=0;


    @Override
    protected int getLayoutId() {
        return R.layout.fg_main_index_content;
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



    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
