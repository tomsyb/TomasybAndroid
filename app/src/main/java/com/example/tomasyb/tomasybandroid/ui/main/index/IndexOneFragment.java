package com.example.tomasyb.tomasybandroid.ui.main.index;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tomasyb.baselib.base.mvp.BaseFragment;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.base.mvp.LazyFragment;
import com.example.tomasyb.baselib.rvadapter.CommonAdapter;
import com.example.tomasyb.baselib.rvadapter.base.ViewHolder;
import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-1-17.17:47
 * @since JDK 1.8
 */

public class IndexOneFragment extends LazyFragment {
    @BindView(R.id.index_onerecycleview)
    RecyclerView mRv;
    private String mTitle;
    private CommonAdapter<String> mAdapter;

    public static IndexOneFragment getInstance(String title) {
        IndexOneFragment sf = new IndexOneFragment();
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
    protected int getLayoutId() {
        return R.layout.fragment_indexone;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }


    @Override
    public void lazyInitView(View view, Bundle savedInstanceState) {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add("我是--》"+i);
        }
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CommonAdapter<String>(getActivity(),R.layout.item_only_text,mList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tvCurrent,s);
            }
        };
        mRv.setAdapter(mAdapter);
    }
}
