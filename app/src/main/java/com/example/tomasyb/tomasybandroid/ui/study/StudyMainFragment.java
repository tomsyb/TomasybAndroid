package com.example.tomasyb.tomasybandroid.ui.study;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.baselib.util.JsonUtils;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.bean.StudyMainEntity;
import com.example.tomasyb.tomasybandroid.common.Common;
import com.example.tomasyb.tomasybandroid.common.Constant;

import java.util.List;

import butterknife.BindView;

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

    private List<StudyMainEntity.RxjavaBean> mRxjavaDatas;//Rxjava数据
    private List<StudyMainEntity.RetrofitBean> mRetrofitDatas;//Retrofit数据
    private int mPostion;
    private CommonRecycleViewAdapter adapter;

    /**
     * 单列获取fragment
     *
     * @param postion 位置
     * @return
     */
    public static StudyMainFragment getInstance(int postion) {
        StudyMainFragment fragment = new StudyMainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TITLE, postion);
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
        mPostion = getArguments().getInt(ARG_TITLE);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        String json = Common.getJson(getActivity(), "rx.json");
        StudyMainEntity bean = JsonUtils.JsonToObject(json, StudyMainEntity.class);
        mRxjavaDatas = bean.getRxjava();
        mRetrofitDatas = bean.getRetrofit();
        if (mPostion == 0){
            initRxjavaAdapter();
        }else {
            initRetrofitAdapter();
        }

    }

    /**
     * Retrofit的适配器
     */
    private void initRetrofitAdapter() {
        adapter = new CommonRecycleViewAdapter<StudyMainEntity.RetrofitBean>(getActivity(),R.layout.item_title_content,mRetrofitDatas){

            @Override
            public void convert(final ViewHolderHelper helper, final StudyMainEntity.RetrofitBean bean) {
                helper.setText(R.id.normal_tv_title,bean.getName());
                helper.setText(R.id.item_content,bean.getContent());
            }
        };
        mRv.setAdapter(adapter);
    }

    /**
     * Rxjava的适配器
     */
    private void initRxjavaAdapter() {
        adapter = new CommonRecycleViewAdapter<StudyMainEntity.RxjavaBean>(getActivity(),R.layout.item_title_content,mRxjavaDatas){

            @Override
            public void convert(final ViewHolderHelper helper, final StudyMainEntity.RxjavaBean bean) {
                helper.setText(R.id.normal_tv_title,bean.getName());
                helper.setText(R.id.item_content,bean.getContent());
                helper.setOnClickListener(R.id.item_ll_content, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(Constant.MAIN_STUDY_RXJAVA)
                                .withInt(Constant.STUDY_TYPE,helper.getPosition())
                                .navigation();
                    }
                });
            }
        };
        mRv.setAdapter(adapter);
    }


}
