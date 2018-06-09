package com.example.tomasyb.tomasybandroid.ui.index.contract;

import com.example.tomasyb.baselib.base.BasePre;
import com.example.tomasyb.baselib.base.IBaseModel;
import com.example.tomasyb.baselib.base.IBaseView;
import com.example.tomasyb.tomasybandroid.bean.NewsSummary;
import com.example.tomasyb.tomasybandroid.ui.main.contract.IndexContract;

import java.util.List;

import rx.Observable;

/**
 * 首页内容Contract
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-7.15:05
 * @since JDK 1.8
 */

public interface IndexContentContract {
    interface Model extends IBaseModel{
        Observable <List<NewsSummary>> getIndexList(String type,String id,int startPage);
    }
    interface View extends IBaseView{
        void changeData(List<NewsSummary> mData);

    }
    abstract class Presenter extends BasePre<View,Model>{
        public abstract void getIndexList(String type,String id,int startPage);
    }
}
