package com.example.tomasyb.tomasybandroid.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tomasyb.baselib.adapter.BaseQuickAdapter;
import com.example.tomasyb.baselib.adapter.BaseViewHolder;
import com.example.tomasyb.baselib.base.mvp.BaseFragment;
import com.example.tomasyb.baselib.util.ActivityUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.main.contact.IndexContact;
import com.example.tomasyb.tomasybandroid.ui.main.presenter.IndexPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.agora.yzxing.ActivityScanerCode;


/**
 * 首页fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.15:50
 * @since JDK 1.8
 */

public class IndexFragment extends BaseFragment<IndexContact.presenter> implements IndexContact
        .view {
    @BindView(R.id.index_main_rv)
    RecyclerView mRv;
    private BaseQuickAdapter<String, BaseViewHolder> mAdaper;
    private List<String> mDatas;

    @Override
    protected int getLayoutResource() {
        return R.layout.fg_main_index;
    }

    @Override
    public IndexContact.presenter initPresenter() {
        return new IndexPresenter(this);
    }


    @Override
    protected void initView() {
        initAdapter();
    }


    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void initAdapter() {
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDatas = new ArrayList<>();
        mDatas.add("BottomBar导航");
        mAdaper = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_btn_only, mDatas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.btn_txt, item);
            }
        };
        mAdaper.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        ARouter.getInstance().build("BottomBarUseActivity").navigation();
                        break;
                }
            }
        });
        mRv.setAdapter(mAdaper);
    }


    @OnClick({R.id.img_scan, R.id.ll_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_scan:
                ActivityUtils.startActivity(ActivityScanerCode.class);
                break;
            case R.id.ll_search:
                break;
        }
    }
}
