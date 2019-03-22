package com.example.tomasyb.tomasybandroid.ui.interview.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.tomasyb.baselib.base.mvp.BaseFragment;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.rvadapter.CommonAdapter;
import com.example.tomasyb.baselib.rvadapter.base.ViewHolder;
import com.example.tomasyb.baselib.rvadapter.wrapper.EmptyWrapper;
import com.example.tomasyb.baselib.util.ActivityUtils;
import com.example.tomasyb.baselib.util.GsonUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.activitylifecycle.LifeCycleActivity;
import com.example.tomasyb.tomasybandroid.ui.androidservice.ServiceMainActivity;
import com.example.tomasyb.tomasybandroid.ui.interview.entity.InterviewListEty;
import com.example.tomasyb.tomasybandroid.ui.outofmemory.MainOutOfMemoryActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yanb.yweb.base.BaseWebActivity;

/**
 * 列表
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-2-28.17:12
 * @since JDK 1.8
 */

public class InterviewListFragment extends BaseFragment{
    @BindView(R.id.interview_rv)
    RecyclerView mRv;
    // 0:技能1：项目2源码
    private int mtype = 0;
    private CommonAdapter mComAdapter;
    private EmptyWrapper mEmptyWrapper;
    private List<InterviewListEty.DatasBean> mList = new ArrayList<>();

    public static InterviewListFragment getInstance(int type) {
        InterviewListFragment sf = new InterviewListFragment();
        sf.mtype = type;
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
        return R.layout.fragment_interview_list;
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
        initAdapter();
        parseData();
    }

    private void initAdapter() {

        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mComAdapter = new CommonAdapter<InterviewListEty.DatasBean>(getActivity(),R.layout.item_interview_main_list,mList) {
            @Override
            protected void convert(ViewHolder holder, InterviewListEty.DatasBean datasBean, int
                    position) {
                holder.setText(R.id.tv_name,datasBean.getName());
                holder.setText(R.id.tv_content,datasBean.getContent());
                holder.setOnClickListener(R.id.ll_root, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("htmlurl",datasBean.getHtmlurl());
                        Intent intent = new Intent(getActivity(),BaseWebActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                holder.setOnClickListener(R.id.tv_entity, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (position){
                            // Activity生命周期
                            case 0:
                                ActivityUtils.startActivity(LifeCycleActivity.class);
                                break;
                            // Service生命周期
                            case 1:
                                ActivityUtils.startActivity(ServiceMainActivity.class);
                                break;
                            // 内存泄漏
                            case 2:
                                ActivityUtils.startActivity(MainOutOfMemoryActivity.class);
                                break;
                        }
                    }
                });
            }
        };
        mEmptyWrapper = new EmptyWrapper(mComAdapter);
        mEmptyWrapper.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.include_noda,mRv,false));
        mRv.setAdapter(mEmptyWrapper);
    }

    /**
     * 解析数据
     */
    private void parseData() {
        try {
            String json = GsonUtils.getJson(getActivity(), "interviewlist.json");
            InterviewListEty bean = GsonUtils.fromJson(json, InterviewListEty.class);
            for (int i = 0; i < bean.getDatas().size(); i++) {
                if (bean.getDatas().get(i).getType()==mtype){
                    mList.add(bean.getDatas().get(i));
                }
            }
            mEmptyWrapper.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
