package com.example.tomasyb.tomasybandroid.ui.index.fragment;

import android.widget.TextView;

import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.ui.index.contract.IndexContentContract;

import butterknife.BindView;

/**
 * 首页滑动内容Fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-7.15:03
 * @since JDK 1.8
 */

public class IndexContentFragment extends BaseFragment<IndexContentContract.Presenter,IndexContentContract.Model>{
    @BindView(R.id.index_content_tv)
    TextView mTvName;
    @Override
    protected int getLayoutResource() {
        return R.layout.fg_main_index_content;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        mTvName.setText(getArguments().getString(Constant.INDEX_TOP_NAME));
    }
}
