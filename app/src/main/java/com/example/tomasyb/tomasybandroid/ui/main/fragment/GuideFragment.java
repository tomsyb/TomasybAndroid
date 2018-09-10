package com.example.tomasyb.tomasybandroid.ui.main.fragment;

import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.tomasybandroid.R;

/**
 * 首页fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.15:50
 * @since JDK 1.8
 */

public class GuideFragment extends BaseFragment{
    @Override
    protected int getLayoutResource() {
        return R.layout.fg_main_guide;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }


    @Override
    protected void initView() {

    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
