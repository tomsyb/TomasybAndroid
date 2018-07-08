package com.example.tomasyb.tomasybandroid.ui.study;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.animation.AlphaInAnimation;
import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.bean.StudyMainEntity;
import com.example.tomasyb.tomasybandroid.ui.study.adapter.StudyMainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 学习主Fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-5.18:19
 * @since JDK 1.8
 */

public class StudyMainFragment extends BaseFragment {
    private static final String ARG_TITLE = "title";
    @BindView(R.id.rv)
    RecyclerView mRv;

    private List<StudyMainEntity> mDatas;//数据
    private String mTitle;
    private StudyMainAdapter adapter;

    /**
     * 单列获取fragment
     *
     * @param title
     * @return
     */
    public static StudyMainFragment getInstance(String title) {
        StudyMainFragment fragment = new StudyMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fg_study_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        mTitle = getArguments().getString(ARG_TITLE);
        initAdapter();
    }

    private void initAdapter() {
        mDatas = new ArrayList<>();
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new StudyMainAdapter(getActivity(), R.layout.item_title_content, mDatas);
        for (int i = 0; i < 18; i++) {
            StudyMainEntity bean = new StudyMainEntity();
            bean.setTitle("标题"+i);
            bean.setContent("内容"+i);
            mDatas.add(bean);
        }
        mRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
