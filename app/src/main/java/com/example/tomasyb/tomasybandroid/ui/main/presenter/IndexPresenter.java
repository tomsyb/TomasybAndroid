package com.example.tomasyb.tomasybandroid.ui.main.presenter;

import com.example.tomasyb.baselib.base.rx.RxSubscriber;
import com.example.tomasyb.tomasybandroid.bean.IndexTable;
import com.example.tomasyb.tomasybandroid.ui.main.contract.IndexContract;

import java.util.List;

/**
 * 首页Presenter
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-5.11:16
 * @since JDK 1.8
 */

public class IndexPresenter extends IndexContract.Presenter{
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void loadIndexTopData() {
        mRxManager.add(mModel.loadIndexTopData().subscribe(new RxSubscriber<List<IndexTable>>(mContext,false) {
            @Override
            protected void _onNext(List<IndexTable> indexTables) {
                mView.changeData(indexTables);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }
}
