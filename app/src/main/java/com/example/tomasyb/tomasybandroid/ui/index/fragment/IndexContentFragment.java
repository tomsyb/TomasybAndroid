package com.example.tomasyb.tomasybandroid.ui.index.fragment;

import com.example.tomasyb.baselib.base.BaseFragment;
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
    protected int getLayoutResource() {
        return R.layout.fg_main_index_content;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {

    }
}
