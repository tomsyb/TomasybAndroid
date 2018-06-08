package com.example.tomasyb.tomasybandroid.ui.index.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.bean.IndexTable;
import com.example.tomasyb.tomasybandroid.bean.NewsSummary;
import com.example.tomasyb.tomasybandroid.bean.Student;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.ui.index.adapter.IndexContentAdapter;
import com.example.tomasyb.tomasybandroid.ui.index.contract.IndexContentContract;
import com.example.tomasyb.tomasybandroid.ui.index.model.IndexContentModel;
import com.example.tomasyb.tomasybandroid.ui.index.presenter.IndexContentPresenter;
import com.example.tomasyb.tomasybandroid.ui.main.model.IndexModel;
import com.example.tomasyb.tomasybandroid.ui.main.presenter.IndexPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * 首页滑动内容Fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-7.15:03
 * @since JDK 1.8
 */

public class IndexContentFragment extends BaseFragment<IndexContentPresenter,IndexContentModel> implements IndexContentContract.View,OnRefreshListener{
    @BindView(R.id.content_irc)
    IRecyclerView mIrc;
    private IndexContentAdapter mAdapter;
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
        mIrc.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatas.clear();
        mAdapter = new IndexContentAdapter(getContext(),mDatas);
        mIrc.setAdapter(mAdapter);
        mIrc.setOnRefreshListener(this);
        //数据为空才重新发起请求
        if(mAdapter.getSize()<=0) {
            mStartPage = 0;
            mPresenter.getIndexList("list","T1348648756099",0);
        }
    }

    @Override
    public void showLoading(String content) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void changeData(List<NewsSummary> newsSummaries) {
        if (newsSummaries != null) {
            mStartPage += 20;
            if (mAdapter.getPageBean().isRefresh()) {
                mIrc.setRefreshing(false);
                mAdapter.replaceAll(newsSummaries);
            } else {
                if (newsSummaries.size() > 0) {
                    mIrc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    mAdapter.addAll(newsSummaries);
                } else {
                    mIrc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        }
    }

    @Override
    public void onRefresh() {

    }
}
