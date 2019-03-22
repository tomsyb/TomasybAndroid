package com.example.tomasyb.tomasybandroid.ui.main.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.main.contact.MapContact;
import com.example.tomasyb.tomasybandroid.ui.main.presenter.MapPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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

    // 图片集合
    int[] iconIds = {R.drawable.img_map_card1, R.drawable.img_map_card2, R.drawable.img_map_card3,
            R.drawable.img_map_card4};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        initMap();
        presenter.addMarketData();
        setTitlePage();
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
            // 设置amap加载成功事件监听器
            aMap.setOnMapLoadedListener(this);
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
     * 这里我们把点定位当前位置
     * 监听amap地图加载成功事件回调
     */
    @Override
    public void onMapLoaded() {

    }

    @Override
    public void addMarkerToMap(List<LatLng> mLatList) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (int i = 0; i < mLatList.size(); i++) {
            Marker marker = aMap.addMarker(new MarkerOptions()
                    .title("点"+i)
                    // 这是只加图片BitmapDescriptorFactory还有其他方法
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_point))
                    // 自定义view展示
//                .icon(BitmapDescriptorFactory.fromView(getMyView(mPlanMap.get(i).getName(),
//                        mPlanMap.get(i).getStatus())))
                    .position(mLatList.get(i))
                    .draggable(false));
            // 这行关键，标记Marker的类型xxx
            marker.setObject("点"+i);
            builder.include(mLatList.get(i));
        }
        // 设置所有maker显示在当前可视区域地图中
        LatLngBounds bounds = builder.build();
        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
    }


    @Override
    public void setTitlePage() {
        List<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add(iconIds[i % iconIds.length]);
        }
        BaseVPAdapter<Integer> baseVPAdapter = new BaseVPAdapter<Integer>(this, R.layout
                .item_map_viewpager, dataList) {
            @Override
            public void bindView(View view, Integer data) {
                ImageView imageView = view.findViewById(R.id.map_card_img);
                imageView.setImageResource(data);
                Button btn = view.findViewById(R.id.btn_show);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showLong("哈哈哈，你被骗了 O(∩_∩)O哈哈~");
                    }
                });
            }
        };
        mapVp.setOffscreenPageLimit(4);
        mapVp.setPageTransformer(false, new VpTranform());
        mapVp.setAdapter(baseVPAdapter);
    }

    @Override
    public void addMapLine(List<LatLng> mLatList) {
        PolylineOptions options = new PolylineOptions();
        for (int i = 0; i < mLatList.size(); i++) {
            options.add(mLatList.get(i));
        }
        options.width(5).color(getResources().getColor(R.color.main));
        aMap.addPolyline(options);
    }

    @Override
    public void clearMarker() {
        List<Marker> mapScreenMarkers = aMap.getMapScreenMarkers();
        for (int i = 0; i < mapScreenMarkers.size(); i++) {
            Marker marker = mapScreenMarkers.get(i);
            marker.remove();
            //根据类型删除
//            if (marker.getObject() instanceof xxx){
//                marker.remove();
//            }
        }
        //aMap.invalidate();//刷新地图
    }

    @OnClick({R.id.img_map_addline, R.id.img_map_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_map_addline:
                presenter.getMapLineData();
                break;
            case R.id.img_map_clear:
                clearMarker();
                break;
        }
    }
}
