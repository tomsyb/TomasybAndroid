package com.example.tomasyb.tomasybandroid.net;

import com.example.tomasyb.tomasybandroid.bean.LoginUser;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 网络请求接口
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-3.11:51
 * @since JDK 1.8
 */

public interface ApiService {

    //--------------------------------------------------------------Retrofit单独使用

    /**
     * 下面是Retrofit单独使用的接口
     * http://scrs.daqsoft.com/api/rest/app/disguiseLogin?account=yanb&password=123456
     */
    @GET("rest/app/disguiseLogin")
    Call<BaseEnty<LoginUser>> getUserMsg(@Query("account") String account,
                              @Query("password") String psd);

    //--------------------------------------------------------------Retrofit+Rxjava结合使用

    /**
     * get
     */
    @GET("rest/app/disguiseLogin")
    Observable<BaseEnty<LoginUser>> getUser(@Query("account") String account,
                                               @Query("password") String psd);

}
