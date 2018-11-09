package com.example.tomasyb.tomasybandroid.ui.main.presenter;

import com.example.tomasyb.baselib.base.mvp.BasePresenter;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.tomasybandroid.ui.main.contact.IndexContact;

/**
 * IndexPresenter
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-9-5.12:35
 * @since JDK 1.8
 */

public class IndexPresenter extends BasePresenter<IndexContact.view> implements IndexContact.presenter{

    public IndexPresenter(IndexContact.view view) {
        super(view);
    }

    @Override
    public void addData() {

    }
}
