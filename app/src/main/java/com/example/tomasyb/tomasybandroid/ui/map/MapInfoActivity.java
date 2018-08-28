package com.example.tomasyb.tomasybandroid.ui.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.common.Constant;

/**
 * 高德地图
 */
public class MapInfoActivity extends AppCompatActivity implements AMap.InfoWindowAdapter,AMap.OnMapLoadedListener {
    private MapView mapView;
    private AMap aMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_info);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        initMap();
        addMarkerToMap();
    }

    /**
     * 添加Marker
     */
    private void addMarkerToMap() {
        Marker marker = aMap.addMarker(new MarkerOptions()
                .title("成都")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_point))
                .position(Constant.CHENGDU)
                .draggable(false));
        // 这行关键，标记Marker的类型xxx
        marker.setObject("我是对象");
    }

    /**
     * 初始化AMap对象
     */
    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setInfoWindowAdapter(this);
            aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
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
        // 多个就添加.include()就可以
        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
    }
}
