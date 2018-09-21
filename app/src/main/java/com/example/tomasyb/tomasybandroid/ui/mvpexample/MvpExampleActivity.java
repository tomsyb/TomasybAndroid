package com.example.tomasyb.tomasybandroid.ui.mvpexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.bean.MvpUseBean;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.contact.MvpContact;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.presenter.MvpUsePresenter;

import java.util.List;

import butterknife.BindView;

/**
 * mvp用法列子
 */
public class MvpExampleActivity extends BaseActivity<MvpContact.presenter> implements MvpContact.view{
    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp_example;
    }

    @Override
    public void initView() {
        presenter.getData();
    }

    @Override
    public MvpContact.presenter initPresenter() {
        return new MvpUsePresenter(this);
    }

    @Override
    public void setData(List<MvpUseBean.StoriesBean> dataList) {
        LogUtils.e(dataList.size());
    }
}
