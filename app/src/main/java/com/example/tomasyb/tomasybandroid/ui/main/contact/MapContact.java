package com.example.tomasyb.tomasybandroid.ui.main.contact;

import com.amap.api.maps.model.LatLng;
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
        void addMarkerToMap(List<LatLng> mLatList);
        void setTitlePage();//设置带标题的卡片布局
        void addMapLine(List<LatLng> mLatList);
        void clearMarker();//清除marker
    }
    interface presenter extends IBasePresenter{
        void addVpData();// 添加卡片布局数据
        void addMarketData();//添加点位数据
        void getMapLineData();//获取线数据
    }

}
