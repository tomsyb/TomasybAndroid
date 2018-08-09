package com.example.tomasyb.tomasybandroid.ui.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.amap.api.maps.MapView;
import com.example.tomasyb.baselib.widget.sidebar.WaveSideBar;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.base.ToolbarsBaseActivity;
import com.example.tomasyb.tomasybandroid.ui.me.entity.MeAddressEty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 通讯录
 */
public class MeAdressListActivity extends ToolbarsBaseActivity {


    @BindView(R.id.me_adress_rv)
    RecyclerView mAdressRv;
    @BindView(R.id.me_adress_wsidebar)
    WaveSideBar mSideBar;
    private List<MeAddressEty> mDatas;

    @Override
    public int getLayoutId() {
        return R.layout.activity_me_adress_list;
    }

    @Override
    public void initView() {

        mAdressRv.setLayoutManager(new LinearLayoutManager(this));
        mDatas = new ArrayList<>();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected String getSubTitle() {
        return "通讯录";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
