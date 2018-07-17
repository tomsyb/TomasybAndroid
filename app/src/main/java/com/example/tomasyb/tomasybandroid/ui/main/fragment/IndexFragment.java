package com.example.tomasyb.tomasybandroid.ui.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
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


/**
 * 首页fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.15:50
 * @since JDK 1.8
 */

public class IndexFragment extends BaseFragment {
    @BindView(R.id.index_rv)
    RecyclerView mRv;

    List<String> mDatas;
    private CommonRecycleViewAdapter<String> mAdapter;
    @Override
    protected int getLayoutResource() {
        return R.layout.fg_main_index;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        mDatas = new ArrayList<>();
        mDatas.add("顶部Title的封装使用");
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CommonRecycleViewAdapter<String>(getActivity(),R.layout.item_text,mDatas) {
            @Override
            public void convert(final ViewHolderHelper helper, String s) {
                helper.setText(R.id.item_btn,s);
                helper.setOnClickListener(R.id.item_btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (helper.getPosition()){
                            case 0:
                                ARouter.getInstance().build(Constant.STUDY_COMUIMAIN).navigation();
                                break;

                        }
                    }
                });
            }
        };
        mRv.setAdapter(mAdapter);
    }


}
