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
import com.example.tomasyb.baselib.yadapter.decoration.DividerItemDecoration;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.main.index.adapter.IndexVpAdapter;
import com.example.tomasyb.tomasybandroid.ui.main.index.bean.IndexVpBean;

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
    private IndexVpAdapter mVpAdapter;
    private List<IndexVpBean> mDatas = new ArrayList<>();
    public static IndexOneFragment getInstance() {
        IndexOneFragment sf = new IndexOneFragment();
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
        for (int i = 0; i < 10; i++) {
            IndexVpBean bean = new IndexVpBean("2018","建筑考试","王麻子",0);
            mDatas.add(bean);
        }
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mVpAdapter = new IndexVpAdapter(getActivity(),mDatas);
        mRv.setAdapter(mVpAdapter);
    }
}
