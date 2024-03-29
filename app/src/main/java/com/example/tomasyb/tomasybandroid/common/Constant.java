package com.example.tomasyb.tomasybandroid.common;

import com.amap.api.maps.model.LatLng;

/**
 * 常量及
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.13:53
 * @since JDK 1.8
 */

public class Constant {
    //-----------------------------------------------------------------------请求基础地址
    public static String BASE_URL = "http://scrs.daqsoft.com/api/";
    public static String WX_BASE_URL = "https://api.weixin.qq.com/sns/";
    public static final String BASE_UPDATAURL = "http://app.daqsoft.com/appserives/";
    public static String STUDY_TYPE = "STUDY_TYPE";//学习页面的类型
    public static String STUDY_TITLE = "STUDY_TITLE";//学习页面的标题

    /**
     * 界面跳转
     */
    public static final String ACTIVITY_LOGIN = "/login/LoginActivity";
    public static final String ACTIVITY_MAIN = "/main/mainActivity";
    public static final String MAIN_STUDY = "/study/main/StudyMainActivity";
    public static final String MAIN_STUDY_RXJAVA = "/study/RxjavaActivity";
    public static final String STUDY_RETROFIT= "/study/RetrofitStudyActivity";
    public static final String STUDY_COMUIMAIN= "/study/ComUiMainActivity";
    public static final String ME_ADRESS_LIST= "/me/";

    /**
     * 首页选中的tab
     */
    public static final String HOME_CURRENT_TAB_POSITION="HOME_CURRENT_TAB_POSITION";
    public static final String INDEX_TOP_DATA="INDEX_TOP_DATA";
    /**
     * 首页基础数据
     */
    public static final String INDEX_TOP_NAME="INDEX_TOP_NAME";

    /**
     * 地图
     */
    public static final LatLng CHENGDU = new LatLng(30.679879, 104.064855);// 成都市经纬度

    //----------------------------------------------------------------登录相关
    /**
     * app_id是从微信官网申请到的合法APPid
     */
    public static final String APP_ID_WX = "wxb363a9ff53731258";
    /**
     * 微信AppSecret值
     */
    public static final String  APP_SECRET_WX = "2b0d0325bb7c8383bff52e0900b7f56c";


}
