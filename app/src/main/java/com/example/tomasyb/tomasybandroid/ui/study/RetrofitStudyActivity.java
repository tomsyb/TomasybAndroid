package com.example.tomasyb.tomasybandroid.ui.study;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.tomasybandroid.R;

@Route(path = "/study/RetrofitStudyActivity")
public class RetrofitStudyActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_retrofit_use;
    }

    @Override
    public void initView() {

    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

}
