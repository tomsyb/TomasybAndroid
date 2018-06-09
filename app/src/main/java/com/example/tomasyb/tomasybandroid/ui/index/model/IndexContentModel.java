package com.example.tomasyb.tomasybandroid.ui.index.model;

import com.example.tomasyb.baselib.base.api.Api;
import com.example.tomasyb.baselib.base.rx.RxSchedulers;
import com.example.tomasyb.baselib.util.TimeUtil;
import com.example.tomasyb.tomasybandroid.api.AppApi;
import com.example.tomasyb.tomasybandroid.bean.NewsSummary;
import com.example.tomasyb.tomasybandroid.ui.index.contract.IndexContentContract;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-8.9:52
 * @since JDK 1.8
 */

public class IndexContentModel implements IndexContentContract.Model {
    @Override
    public Observable<List<NewsSummary>> getIndexList(String type, final String id, int startPage) {
        return AppApi.getService().getNewsList(Api.getCacheControl(),type,id,startPage)
                .flatMap(new Func1<Map<String, List<NewsSummary>>, Observable<NewsSummary>>() {
                    @Override
                    public Observable<NewsSummary> call(Map<String, List<NewsSummary>> map) {
                        if (id.endsWith("5YyX5Lqs")){
                            return Observable.from(map.get("北京"));
                        }
                        return Observable.from(map.get(id));
                    }
                })
                //转化时间啊
                .map(new Func1<NewsSummary, NewsSummary>() {
                    @Override
                    public NewsSummary call(NewsSummary newsSummary) {
                        String ptime = TimeUtil.formatDate(newsSummary.getPtime());
                        newsSummary.setPtime(ptime);
                        return newsSummary;
                    }
                })
                .distinct()
                .toSortedList(new Func2<NewsSummary, NewsSummary, Integer>() {
                    @Override
                    public Integer call(NewsSummary newsSummary, NewsSummary newsSummary2) {
                        return newsSummary2.getPtime().compareTo(newsSummary.getPtime());
                    }
                })
                //声明线程调度
                .compose(RxSchedulers.<List<NewsSummary>>io_main());

    }
}
