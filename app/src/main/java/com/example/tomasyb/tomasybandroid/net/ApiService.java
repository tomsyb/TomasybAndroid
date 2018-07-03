package com.example.tomasyb.tomasybandroid.net;

import com.example.tomasyb.tomasybandroid.bean.MeiZi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * 网络请求接口
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-3.11:51
 * @since JDK 1.8
 */

public interface ApiService {
    /**
     * 测试地址
     */
    @Headers("Cache-Control: public, max-age=100")
    @GET("福利/10/1")
    Observable<List<MeiZi>> getMeizi();
}
