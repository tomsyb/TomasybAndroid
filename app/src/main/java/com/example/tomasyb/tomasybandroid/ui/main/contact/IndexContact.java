package com.example.tomasyb.tomasybandroid.ui.main.contact;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.base.mvp.IBaseView;
import com.example.tomasyb.tomasybandroid.ui.main.entity.IndexMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-9-5.11:46
 * @since JDK 1.8
 */

public interface IndexContact {
    // view
    interface view extends IBaseView {
        void initRefreshLayout();
        void initViews();
        void initBanner();
        void dealWithViewPager();
        //获取fragment集合
        List<Fragment> getFragments();
        // 获取context
        FragmentActivity getContexts();
        // 设置弹框的数据
        void setDialogData(List<IndexMenu.DataBean> list);
    }
    //p
    interface presenter extends IBasePresenter {
        // 加载本地图片不用网络请求
        ArrayList<Integer> getLocationBannerData();

        /**
         * 获取弹框数据
         */
        void getDialogData();
    }
}
