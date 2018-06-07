package com.example.tomasyb.tomasybandroid.ui.main.model;

import com.example.tomasyb.baselib.base.rx.RxSchedulers;
import com.example.tomasyb.baselib.base.rx.RxSubscriber;
import com.example.tomasyb.baselib.util.ACache;
import com.example.tomasyb.tomasybandroid.base.IApplication;
import com.example.tomasyb.tomasybandroid.bean.IndexTable;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.db.AppLocalManager;
import com.example.tomasyb.tomasybandroid.ui.main.contract.IndexContract;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-7.10:31
 * @since JDK 1.8
 */

public class IndexModel implements IndexContract.Model {

    @Override
    public Observable<List<IndexTable>> loadIndexTopData() {
        return Observable.create(new Observable.OnSubscribe<List<IndexTable>>() {
            @Override
            public void call(Subscriber<? super List<IndexTable>> subscriber) {
                ArrayList<IndexTable> indexData = (ArrayList<IndexTable>) ACache.get(IApplication.getAppContext()).getAsObject(Constant.INDEX_TOP_DATA);
                if (indexData ==null){
                    //我们从缓存中取
                    indexData = (ArrayList<IndexTable>) AppLocalManager.getIndexTopStatic();
                    ACache.get(IApplication.getAppContext()).put(Constant.INDEX_TOP_DATA,indexData);
                }
                subscriber.onNext(indexData);
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<List<IndexTable>>io_main());
    }
}
