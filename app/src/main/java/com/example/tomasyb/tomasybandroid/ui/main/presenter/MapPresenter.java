package com.example.tomasyb.tomasybandroid.ui.main.presenter;

import com.amap.api.maps.model.LatLng;
import com.example.tomasyb.baselib.base.mvp.BasePresenter;
import com.example.tomasyb.tomasybandroid.ui.main.contact.MapContact;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-11-7.19:18
 * @since JDK 1.8
 */

public class MapPresenter extends BasePresenter<MapContact.view> implements MapContact.presenter {
    public MapPresenter(MapContact.view view) {
        super(view);
    }

    @Override
    public void addVpData() {

    }

    @Override
    public void addMarketData() {
        List<LatLng> mLatlngList  = new ArrayList<>();
        LatLng lat1 = new LatLng(30.679879, 104.064855);
        LatLng lat2 = new LatLng(30.779879, 104.164855);
        LatLng lat3 = new LatLng(30.789879, 104.064855);
        mLatlngList.add(lat1);
        mLatlngList.add(lat2);
        mLatlngList.add(lat3);
        view.addMarkerToMap(mLatlngList);
    }

    @Override
    public void getMapLineData() {
        List<LatLng> mLatlngList  = new ArrayList<>();
        LatLng lat1 = new LatLng(30.679879, 104.064855);
        LatLng lat2 = new LatLng(30.779879, 104.164855);
        LatLng lat3 = new LatLng(30.789879, 104.064855);
        mLatlngList.add(lat1);
        mLatlngList.add(lat2);
        mLatlngList.add(lat3);
        view.addMapLine(mLatlngList);
    }
}
