package com.example.tomasyb.tomasybandroid.ui.main.contact;

import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.base.mvp.IBaseView;

import java.util.ArrayList;

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
        void initAdapter();
        void initBanner();
    }
    //p
    interface presenter extends IBasePresenter {
        // 加载本地图片不用网络请求
        ArrayList<Integer> getLocationBannerData();
    }
}
