package com.example.tomasyb.tomasybandroid.ui.main.contact;

import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.base.mvp.IBaseView;

/**
 * 首页
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-9-5.11:46
 * @since JDK 1.8
 */

public interface IndexContact {
    // view
    interface view extends IBaseView {
        void initAdapter();
    }
    //p
    interface presenter extends IBasePresenter {

    }
}
