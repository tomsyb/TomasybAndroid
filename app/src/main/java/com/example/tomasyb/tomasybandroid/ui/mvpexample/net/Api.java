package com.example.tomasyb.tomasybandroid.ui.mvpexample.net;


import com.example.tomasyb.baselib.base.retrofit.BaseApiImpl;
import com.example.tomasyb.tomasybandroid.common.Constant;

/*
 * 项目名:    BaseFrame
 * 包名       com.zhon.frame.api
 * 文件名:    Api
 * 创建者:    yanb
 * 创建时间:  2017/7/6 on 14:30
 * 描述:     TODO 基础网络访问
 */
public class Api extends BaseApiImpl {

    private static Api api = new Api(Constant.BASE_URL);

    public Api(String baseUrl) {
        super(baseUrl);
    }

    public static RetrofitService getInstance() {

        return api.getRetrofit().create(RetrofitService.class);
    }

}
