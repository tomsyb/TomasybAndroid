package com.example.tomasyb.tomasybandroid.ui.mvpexample.net;


import com.example.tomasyb.baselib.base.mvp.BaseBean;
import com.example.tomasyb.tomasybandroid.ui.rxjava.entity.User;
import com.example.tomasyb.tomasybandroid.ui.rxjava.entity.Video;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * 项目名:    playstock
 * 包名       com.hjtech.playstock.Api
 * 文件名:    RetrofitService
 * 创建者:    严博
 * 创建时间:  2017/7/6 on 14:30 
 * 描述:     TODO
 */
public interface RetrofitService {

    String BASE_URL = "http://scrs.daqsoft.com/api/";

    /**
     * 登录请求
     * http://scrs.daqsoft.com/api/rest/app/login?account=yinh%20&password=123456
     */
    @GET("rest/app/disguiseLogin")
    Observable<BaseBean<User>> getLogin(@Query("account") String account,
                                        @Query("password") String password);

    /**
     * 获取监控列表
     * http://scrs.daqsoft.com/api/rest/monitor/getMonitorGroup?vcode=d80f699c062c8662fad3df86024e246c
     *
     * @param vcode 景区vcode
     * @return
     */
    @GET("rest/monitor/getMonitorGroup")
    Observable<BaseBean<Video>> getVideoList(@Query("vcode") String vcode);

}