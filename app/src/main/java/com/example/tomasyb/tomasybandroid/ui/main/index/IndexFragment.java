package com.example.tomasyb.tomasybandroid.ui.main.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomasyb.baselib.base.mvp.BaseFragment;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.main.contact.IndexContact;
import com.example.tomasyb.tomasybandroid.ui.main.index.holder.LocalImageHolderView;
import com.example.tomasyb.tomasybandroid.ui.main.presenter.IndexPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.agora.yview.banner.ConvenientBanner;
import io.agora.yview.banner.holder.CBViewHolderCreator;
import io.agora.yview.banner.holder.Holder;
import io.agora.yview.banner.listener.OnItemClickListener;


/**
 * 首页fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.15:50
 * @since JDK 1.8
 */

public class IndexFragment extends BaseFragment<IndexContact.presenter> implements IndexContact
        .view , OnItemClickListener {

    @BindView(R.id.index_banner)
    ConvenientBanner mBanner;

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
        initBanner();
    }


    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initBanner() {
        mBanner.setPages(new CBViewHolderCreator() {
            @Override
            public LocalImageHolderView createHolder(View itemView) {
                return new LocalImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_banner_img;
            }
        },mPresenter.getLocationBannerData())
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.bga_banner_point_disabled,R.drawable.bga_banner_point_enabled,})
                .setOnItemClickListener(this)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //      .setOnPageChangeListener(this)设置翻页监听
        //        convenientBanner.setManualPageable(false);//设置不能手动影响
    }

    @Override
    public void onItemClick(int position) {
        ToastUtils.showLong("你点击的是"+position);
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        mBanner.startTurning();
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        mBanner.stopTurning();
    }
}
