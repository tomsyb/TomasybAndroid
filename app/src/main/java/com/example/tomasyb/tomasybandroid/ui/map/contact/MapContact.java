package com.example.tomasyb.tomasybandroid.ui.map.contact;

import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.base.mvp.IBaseView;

import java.util.List;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-11-7.19:16
 * @since JDK 1.8
 */

public interface MapContact {
    interface view extends IBaseView{
        void addMarkerToMap();
        void setTitlePage();//设置带标题的卡片布局
    }
    interface presenter extends IBasePresenter{
        void addVpData();// 添加卡片布局数据
    }

}
