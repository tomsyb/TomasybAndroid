package com.example.tomasyb.tomasybandroid.ui.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.ui.map.cardvp.CardItem;
import com.example.tomasyb.tomasybandroid.ui.map.cardvp.CardPagerAdapter;
import com.example.tomasyb.tomasybandroid.ui.map.cardvp.ShadowTransformer;
import com.example.tomasyb.tomasybandroid.ui.map.contact.MapContact;
import com.example.tomasyb.tomasybandroid.ui.map.pre.MapPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * 高德地图
 */
public class MapInfoActivity extends BaseActivity<MapContact.presenter> implements MapContact
        .view, AMap.InfoWindowAdapter, AMap.OnMapLoadedListener {

    private MapView mapView;
    private AMap aMap;
    private UiSettings mUiSettings;
    // 底部轮滑菜单
    @BindView(R.id.map_vp)
    ViewPager mapVp;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        initMap();
        addMarkerToMap();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_map_info;
    }

    @Override
    public void initView() {
        presenter.addVpData();
    }

    @Override
    public MapContact.presenter initPresenter() {
        return new MapPresenter(this);
    }


    /**
     * 初始化AMap对象
     */
    private void initMap() {

        if (aMap == null) {
            aMap = mapView.getMap();
            mUiSettings = aMap.getUiSettings();
            aMap.setInfoWindowAdapter(this);
            aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
            // 是否显示缩放按钮
            mUiSettings.setZoomControlsEnabled(false);
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * --------------------------------------------------------自定义弹窗
     */
    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(
                R.layout.item_map_infowindow, null);
        render(marker, infoWindow);
        return infoWindow;
    }

    /**
     * --------------------------------------------------------地图自定义view
     *
     * @return
     */
//    protected View getMyView(String txt,int status) {
//        View view = getLayoutInflater().inflate(R.layout.item_an_overlay, null);
//        mTvOverlay = (TextView) view.findViewById(R.id.item_index_tvan);
//        mImgOverlay = (ImageView) view.findViewById(R.id.item_map_imgan);
//        if (status == 1){
//            mTvOverlay.setTextColor(getResources().getColor(R.color.text));
//        }else {
//            mTvOverlay.setTextColor(getResources().getColor(R.color.main));
//        }
//        mImgOverlay.setImageResource(R.mipmap.site);
//        mTvOverlay.setText(txt);
//        return view;
//    }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {
        String str = (String) marker.getObject();
        TextView mapTitle = (TextView) view.findViewById(R.id.map_title);
        TextView mapContent = (TextView) view.findViewById(R.id.map_content);
        mapTitle.setText(str);
        mapContent.setText(str);
    }

    /**
     * --------------------------------------------------监听amap地图加载成功事件回调
     * 这里我们把点定位当前位置
     */
    @Override
    public void onMapLoaded() {
        // 设置所有maker显示在当前可视区域地图中
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(Constant.CHENGDU).build();
        // 多个就添加.include()就可以，如果是list存点位我们可以建立LatLngBounds.Builder对象for循环include再build()
        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
    }

    @Override
    public void addMarkerToMap() {
        Marker marker = aMap.addMarker(new MarkerOptions()
                .title("成都")
                // 这是只加图片BitmapDescriptorFactory还有其他方法
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_point))
                // 自定义view展示
//                .icon(BitmapDescriptorFactory.fromView(getMyView(mPlanMap.get(i).getName(),
//                        mPlanMap.get(i).getStatus())))
                .position(Constant.CHENGDU)
                .draggable(false));
        // 这行关键，标记Marker的类型xxx
        marker.setObject("我是对象");
    }

    @Override
    public void setVpData(List<CardItem> cardItemList) {
        mCardAdapter = new CardPagerAdapter();
        for (int i = 0; i < cardItemList.size(); i++) {
            mCardAdapter.addCardItem(cardItemList.get(i));
        }
        mCardShadowTransformer = new ShadowTransformer(mapVp, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);//卡片布局是否凸起
        mapVp.setAdapter(mCardAdapter);
        mapVp.setPageTransformer(false, mCardShadowTransformer);
        mapVp.setOffscreenPageLimit(3);
    }
}
