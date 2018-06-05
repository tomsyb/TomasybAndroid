package com.example.tomasyb.tomasybandroid.ui.main.contract;

import com.example.tomasyb.baselib.base.IBaseModel;
import com.example.tomasyb.baselib.base.IBaseView;
import com.example.tomasyb.tomasybandroid.bean.IndexTable;

import java.util.List;

import rx.Observable;

/**
 * 首页Contract
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-5.11:17
 * @since JDK 1.8
 */

public interface IIndexContract {
    interface Model extends IBaseModel{
        Observable<List<IndexTable>> lodeIndexData();
    }
    interface View extends IBaseView{
        void changeData(List<IndexTable> data);
    }

}
