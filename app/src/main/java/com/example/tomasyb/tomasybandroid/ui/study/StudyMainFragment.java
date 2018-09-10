package com.example.tomasyb.tomasybandroid.ui.study;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tomasyb.baselib.adapter.BaseQuickAdapter;
import com.example.tomasyb.baselib.adapter.BaseViewHolder;
import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.util.GsonUtils;
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
    private BaseQuickAdapter<StudyMainEntity.RetrofitBean,BaseViewHolder> adapter;

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
    public IBasePresenter initPresenter() {
        return null;
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
        GsonUtils.fromJson(json, StudyMainEntity.class);
        StudyMainEntity bean =  GsonUtils.fromJson(json, StudyMainEntity.class);
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
        adapter = new BaseQuickAdapter<StudyMainEntity.RetrofitBean, BaseViewHolder>(R.layout.item_title_content,mRetrofitDatas) {
            @Override
            protected void convert(BaseViewHolder helper, StudyMainEntity.RetrofitBean item) {
                helper.setText(R.id.normal_tv_title,item.getName());
                helper.setText(R.id.item_content,item.getContent());
            }
        };
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance().build(Constant.STUDY_RETROFIT)
                        .navigation();
            }
        });

        mRv.setAdapter(adapter);
    }

    /**
     * Rxjava的适配器
     */
    private void initRxjavaAdapter() {


        mRv.setAdapter(adapter);
    }


    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
