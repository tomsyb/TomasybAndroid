package com.example.tomasyb.tomasybandroid.ui.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.tomasyb.baselib.base.mvp.BaseFragment;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;

/**
 * SegmentTabLayout的fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-2-27.16:33
 * @since JDK 1.8
 */

public class SimpleSegmentFragment extends BaseFragment{
    @BindView(R.id.tv_segmetn)
    TextView mTvShow;

    private String title = "";

    public static SimpleSegmentFragment getInstance(String title_) {
        SimpleSegmentFragment sf = new SimpleSegmentFragment();
        sf.title = title_;
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
        return R.layout.fg_segmenttab;
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
        mTvShow.setText(title);
    }
}
