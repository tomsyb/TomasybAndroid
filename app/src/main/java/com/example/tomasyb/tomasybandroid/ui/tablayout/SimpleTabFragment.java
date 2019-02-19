package com.example.tomasyb.tomasybandroid.ui.tablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.base.mvp.LazyFragment;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-2-19.17:52
 * @since JDK 1.8
 */

public class SimpleTabFragment extends LazyFragment {
    @BindView(R.id.tv_show_tab)
    TextView tvShowTab;
    private String mTitle;

    public static SimpleTabFragment getInstance(String title) {
        SimpleTabFragment sf = new SimpleTabFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void lazyInitView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_tab_use;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvShowTab.setText(mTitle);
    }
}
