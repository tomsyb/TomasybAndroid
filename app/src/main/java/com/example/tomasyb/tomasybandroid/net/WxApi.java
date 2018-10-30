package com.example.tomasyb.tomasybandroid.net;

import com.example.tomasyb.baselib.base.retrofit.BaseApiImpl;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.net.RetrofitService;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-10-30.17:43
 * @since JDK 1.8
 */

public class WxApi extends BaseApiImpl {
    private static WxApi api = new WxApi(Constant.WX_BASE_URL);
    public WxApi(String baseUrl) {
        super(baseUrl);
    }
    public static RetrofitService getInstance() {
        return api.getRetrofit().create(RetrofitService.class);
    }
}
