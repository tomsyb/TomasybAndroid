package com.example.tomasyb.tomasybandroid.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomasyb.baselib.base.mvp.BaseFragment;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.widget.fab.FloatingActionButton;
import com.example.tomasyb.baselib.widget.fab.FloatingActionMenu;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.imgselect.ImgSelectActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 首页fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.15:50
 * @since JDK 1.8
 */

public class MeFragment extends BaseFragment {


    @BindView(R.id.fab1)
    FloatingActionButton fab1;
    @BindView(R.id.fab2)
    FloatingActionButton fab2;
    @BindView(R.id.fab3)
    FloatingActionButton fab3;
    @BindView(R.id.fab4)
    FloatingActionButton fab4;
    @BindView(R.id.fab5)
    FloatingActionButton fab5;
    @BindView(R.id.menu_red)
    FloatingActionMenu menuRed;
    Unbinder unbinder;

    @Override
    protected int getLayoutResource() {
        return R.layout.fg_main_me;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }


    @Override
    protected void initView() {
        menuRed.setClosedOnTouchOutside(true);// 点击外部关闭
    }


    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }


    @OnClick({R.id.fab1, R.id.fab2, R.id.fab3, R.id.fab4, R.id.fab5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab1:
                startActivity(new Intent(getActivity(), ImgSelectActivity.class));
                break;
            case R.id.fab2:
                break;
            case R.id.fab3:
                break;
            case R.id.fab4:
                break;
            case R.id.fab5:
                break;
        }
    }
}
