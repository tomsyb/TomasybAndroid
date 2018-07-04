package com.example.tomasyb.tomasybandroid.net;

import com.example.tomasyb.tomasybandroid.bean.LoginUser;
import com.example.tomasyb.tomasybandroid.bean.MeiZi;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
    /**
     * 测试地址
     */
    @Headers("Cache-Control: public, max-age=100")
    @GET("福利/10/1")
    Observable<List<MeiZi>> getMeizi();

    /**
     * 下面是Retrofit单独使用的接口
     */
    @GET("rest/app/disguiseLogin")
    Call<LoginUser> getUserMsg(@Query("account") String account, @Query("password") String psd);
}
