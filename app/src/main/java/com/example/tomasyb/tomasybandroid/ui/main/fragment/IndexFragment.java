package com.example.tomasyb.tomasybandroid.ui.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.baselib.base.adapter.BaseFragmentAdapter;
import com.example.tomasyb.baselib.util.TabLayoutUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.bean.IndexTable;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.ui.index.fragment.IndexContentFragment;
import com.example.tomasyb.tomasybandroid.ui.main.contract.IndexContract;
import com.example.tomasyb.tomasybandroid.ui.main.model.IndexModel;
import com.example.tomasyb.tomasybandroid.ui.main.presenter.IndexPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * 首页fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.15:50
 * @since JDK 1.8
 */

public class IndexFragment extends BaseFragment<IndexPresenter, IndexModel> implements IndexContract.View {
    @BindView(R.id.index_fg_vp)
    ViewPager mViewPager;
    @BindView(R.id.index_fg_tablayout)
    TabLayout mTab;
    Unbinder unbinder;
    private BaseFragmentAdapter mFragmentPageAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fg_main_index;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        mPresenter.loadIndexTopData();
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
    }

    @Override
    public void showLoading(String content) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void changeData(List<IndexTable> data) {
        if (data != null) {
            List<String> nameList = new ArrayList<>();
            List<Fragment> mIndexFragmentList = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                nameList.add(data.get(i).getIndexTopName());
                mIndexFragmentList.add(createFragment(data.get(i)));
            }
            if (mFragmentPageAdapter == null) {//没有先创建
                mFragmentPageAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mIndexFragmentList, nameList);
            } else {//有就直接用
                mFragmentPageAdapter.setFragments(getChildFragmentManager(), mIndexFragmentList, nameList);
            }
            mViewPager.setAdapter(mFragmentPageAdapter);
            mTab.setupWithViewPager(mViewPager);
            TabLayoutUtils.dynamicSetTabLayoutMode(mTab);
            setPageChangeListener();
        }
    }

    /**
     * 设置viewPager的监听
     */
    private void setPageChangeListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 创建首页子fragment
     *
     * @param table 需要的对象
     * @return
     */
    private IndexContentFragment createFragment(IndexTable table) {
        IndexContentFragment fragment = new IndexContentFragment();
        //这里可以使用Bundle传数据
        Bundle bundle = new Bundle();
        bundle.putString(Constant.INDEX_TOP_NAME, table.getIndexTopName());
        fragment.setArguments(bundle);
        return fragment;
    }

}
