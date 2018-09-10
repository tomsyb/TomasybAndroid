package com.example.tomasyb.tomasybandroid.net;

import com.example.tomasyb.tomasybandroid.bean.LoginUser;
import com.example.tomasyb.tomasybandroid.ui.rxjava.entity.User;
import com.example.tomasyb.tomasybandroid.ui.study.entity.UpdateEntity;

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
    Observable<LoginUser> getUser(@Query("account") String account,
                                               @Query("password") String psd);

    /**
     * 更新用的接口
     * Services.aspx?AppId=81383&Method=AppVersion&token=daqsoft&AppType=1&VersionCode=2.0.0
     * @param AppId
     * @param Method
     * @param token
     * @param AppType
     * @param VersionCode
     * @return
     */
    @GET("Services.aspx")
    Call<UpdateEntity> getUpdateMsg(@Query("AppId") String AppId,
                                          @Query("Method") String Method,
                                          @Query("token") String token,
                                          @Query("AppType") String AppType,
                                          @Query("VersionCode") String VersionCode);


    /**
     * 登录请求
     * http://scrs.daqsoft.com/api/rest/app/login?account=yinh%20&password=123456
     *
     */
    @GET("rest/app/disguiseLogin")
    Observable<BaseEnty<User>> getLogin(@Query("account") String account,
                                        @Query("password") String password);

}
