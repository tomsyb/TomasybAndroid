# 高德地图应用

----------

公司账号

18981903794 daqappsoft

15928158929 daqsoft

## **目录API**

| 方法 | 序号 | 备注 |
| ------------- |:-------------| :-----|
| 高德地图多个Marker标记自动缩放全部显示在屏幕中|1| ... |

### **1.高德地图多个Marker标记自动缩放全部显示在屏幕中**


```
LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
for(int i=0;i<markers.size();i++){
    boundsBuilder.include(markers.get(i).getPosition());//把所有点都include进去（LatLng类型）
}
aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 15));//第二个参数为四周留空宽度

```

