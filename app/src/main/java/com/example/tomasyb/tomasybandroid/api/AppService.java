package com.example.tomasyb.tomasybandroid.api;

import com.example.tomasyb.tomasybandroid.bean.NewsSummary;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 请求参数
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-8.14:49
 * @since JDK 1.8
 */

public interface AppService {
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsSummary>>> getNewsList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);

}
