package com.example.tomasyb.tomasybandroid.ui.index.presenter;

import com.example.tomasyb.baselib.base.BasePre;
import com.example.tomasyb.baselib.base.rx.RxSubscriber;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.bean.NewsSummary;
import com.example.tomasyb.tomasybandroid.ui.index.contract.IndexContentContract;

import java.util.List;

/**
 * 首页内容P层
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-7.15:04
 * @since JDK 1.8
 */

public class IndexContentPresenter extends IndexContentContract.Presenter {


    @Override
    public void getIndexList(String type, String id, int startPage) {
        mRxManager.add(mModel.getIndexList(type,id,startPage).subscribe(new RxSubscriber<List<NewsSummary>>(mContext,false) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.b_loading));
            }

            @Override
            protected void _onNext(List<NewsSummary> newsSummaries) {
                mView.changeData(newsSummaries);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }
}
